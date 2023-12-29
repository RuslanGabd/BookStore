package com.ruslan.userService;


import com.ruslan.controller.webExceptions.UserExistException;
import com.ruslan.userEntity.User;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface IUserService extends UserDetailsService {

    public boolean userExists(String username);

    public Optional<User> saveUser(User user) throws UserExistException;

    @PostAuthorize("returnObject.username == authentication.name")
    User getUser(Integer id);

    public List<User> getList();

    boolean deleteUser(Integer userId);


}
