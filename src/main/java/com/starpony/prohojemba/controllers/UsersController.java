package com.starpony.prohojemba.controllers;

import com.starpony.prohojemba.converters.UserConverter;
import com.starpony.prohojemba.services.UsersService;
import com.starpony.prohojemba.dto.UserCurrentDto;
import com.starpony.prohojemba.dto.ProfileEditDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/@me", method = RequestMethod.GET)
    public UserCurrentDto getCurrentUser() {
        int currentUserId = 1; // TODO заменить на получение user id из токена
        return UserConverter.mapToCurrentUserDto(usersService.getOne(currentUserId));
    }

    @RequestMapping(value = "/@me", method = RequestMethod.PUT)
    public UserCurrentDto updateCurrentUser(@RequestBody ProfileEditDto profileEditDto) {
        int currentUserId = 1; // TODO заменить на получение user id из токена
        return UserConverter.mapToCurrentUserDto(usersService.update(currentUserId, profileEditDto));
    }
}
