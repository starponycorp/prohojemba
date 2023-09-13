package com.starpony.prohojemba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public void createUpdateCurrentUserEmailRequest() {
    }

    @RequestMapping(value = "/email", method = RequestMethod.PUT)
    public void processUpdateCurrentUserEmailRequest() {
    }

    @RequestMapping(value = "/password/restore", method = RequestMethod.POST)
    public void createRestorePasswordRequest() {
    }

    @RequestMapping(value = "/password/restore", method = RequestMethod.PUT)
    public void processRestorePasswordRequest() {
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public void updateCurrentUserPassword() {
    }
}
