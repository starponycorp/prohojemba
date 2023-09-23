package com.starpony.prohojemba.controllers;

import com.starpony.prohojemba.converters.UserConverter;
import com.starpony.prohojemba.dto.AccountEditDto;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.services.AuthService;
import com.starpony.prohojemba.services.UsersService;
import com.starpony.prohojemba.dto.UserCurrentDto;
import com.starpony.prohojemba.dto.ProfileEditDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class UsersController {
    private final UsersService usersService;
    private final AuthService authService;

    @Autowired
    public UsersController(UsersService usersService, AuthService authService) {
        this.usersService = usersService;
        this.authService = authService;
    }

    @RequestMapping(value = "/@me", method = RequestMethod.GET)
    public UserCurrentDto getCurrentUser() {
        Account account = authService.getAuthenticatedAccount();
        return UserConverter.mapToCurrentUserDto(usersService.getOne(account.getId()));
    }

    @RequestMapping(value = "/@me", method = RequestMethod.PUT)
    public UserCurrentDto updateCurrentUser(@RequestBody AccountEditDto accountEditDto) {
        int currentUserId = 1; // TODO заменить на получение user id из токена
        return UserConverter.mapToCurrentUserDto(usersService.update(currentUserId, accountEditDto));
    }
}
