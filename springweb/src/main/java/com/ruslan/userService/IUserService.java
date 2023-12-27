package com.ruslan.userService;


import com.ruslan.controller.webExceptions.UserExistException;
import com.ruslan.userEntity.User;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IUserService extends UserDetailsService {

    public boolean userExists(String username);

    public boolean saveUser(User user) throws UserExistException;

    @PostAuthorize("returnObject.username == authentication.name")
    User getUser(Integer id);

    public List<User> getList();

    boolean deleteUser(Integer userId);


}
