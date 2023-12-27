package com.ruslan.userDao;

import com.ruslan.userEntity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class UserDto {

    private String userName;
    private String password;
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userName, userDto.userName) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", role=" + role +
                '}';
    }
}
