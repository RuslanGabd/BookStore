package com.ruslan.userService;

import com.ruslan.controller.webExceptions.UserExistException;
import com.ruslan.jwt.JwtGenerator;
import com.ruslan.userDao.RoleRepository;
import com.ruslan.userDao.UserRepository;
import com.ruslan.userEntity.Role;
import com.ruslan.userEntity.User;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtGenerator jwtGenerator;


    public String login(User user) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {
            log.error("Incorrect username or password");
        }
        return jwtGenerator.generateToken(authentication);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getListRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean saveUser(User user) throws UserExistException {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserExistException(user.getUsername());
        }
        Role role = roleRepository.findByName("USER").orElse(null);
        user.setListRoles(Collections.singletonList(role));
        userRepository.createUser(user);
        return true;
    }


    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public User getUser(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.delete(userId);
            return true;
        }
        return false;
    }


}
