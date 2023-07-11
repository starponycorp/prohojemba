package com.starpony.prohojemba.users;

import com.starpony.prohojemba.users.dto.CurrentUserDto;
import com.starpony.prohojemba.users.dto.EditProfileDto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/users")
public class UsersController {
    @RequestMapping(value = "/@me", method = RequestMethod.GET)
    public CurrentUserDto getCurrentUser() {
        return null;
    }

    @RequestMapping(value = "/@me", method = RequestMethod.PUT)
    public CurrentUserDto updateCurrentUser(@RequestBody EditProfileDto editProfileDto) {
        return null;
    }
}
