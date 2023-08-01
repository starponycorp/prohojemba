package com.starpony.prohojemba.controllers;

import com.starpony.prohojemba.users.UserService;
import com.starpony.prohojemba.users.dto.CurrentUserDto;
import com.starpony.prohojemba.users.dto.EditProfileDto;

import com.starpony.prohojemba.users.dto.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/@me", method = RequestMethod.GET)
    public CurrentUserDto getCurrentUser() {
        int currentUserId = 1; // TODO заменить на получение user id из токена
        return UserDtoMapper.mapToCurrentUserDto(userService.getOne(currentUserId));
    }

    @RequestMapping(value = "/@me", method = RequestMethod.PUT)
    public CurrentUserDto updateCurrentUser(@RequestBody EditProfileDto editProfileDto) {
        int currentUserId = 1; // TODO заменить на получение user id из токена
        return UserDtoMapper.mapToCurrentUserDto(userService.updateProfile(currentUserId, editProfileDto));
    }
}
