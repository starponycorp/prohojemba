package com.starpony.prohojemba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerNewUser() {}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginInExistingUser() {}

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public void updateAuthTokensPair() {}
}
