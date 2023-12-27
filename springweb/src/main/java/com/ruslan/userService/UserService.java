package com.ruslan.userService;

import com.ruslan.jwt.JwtGenerator;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Log4j2
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        return userRepository.findByUserName(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUserName(username).isPresent();
    }

    @Override
    public boolean saveUser(User user) {
        if (userRepository.findByUserName(user.getUsername()).isPresent()) {
            return false;
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
