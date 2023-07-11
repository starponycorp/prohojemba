package com.starpony.prohojemba.users.exceptions;

import com.starpony.prohojemba.errors.exceptions.ItemNotFoundException;


public class UserNotFoundException extends ItemNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
