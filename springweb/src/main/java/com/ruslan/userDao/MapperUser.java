package com.ruslan.userDao;

import com.ruslan.userEntity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MapperUser {

    private final PasswordEncoder passwordEncoder;

    public UserDto userToDto(User user) {
        return new UserDto(user.getUsername(),
                user.getPassword());
    }

    public User userDtoToUser(UserDto userDto) {
        return new User(userDto.getUserName(),
                userDto.getPassword());
    }
}
