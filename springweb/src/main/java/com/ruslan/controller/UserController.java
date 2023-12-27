package com.ruslan.controller;

import com.ruslan.jwt.JwtDto;
import com.ruslan.userDao.MapperUser;
import com.ruslan.userDao.UserDto;
import com.ruslan.userEntity.User;
import com.ruslan.userService.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MapperUser mapperUser;

    @PostMapping("login")
    public JwtDto login(@RequestBody UserDto userDto) {
        return new JwtDto(userService.login(mapperUser.userDtoToUser(userDto)));
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        boolean flag = userService.saveUser(userForm);
        if (!flag) {
            model.addAttribute("usernameError", "User with such name is already exist");
            return "registration";
        }

        return "redirect:/";
    }


}
