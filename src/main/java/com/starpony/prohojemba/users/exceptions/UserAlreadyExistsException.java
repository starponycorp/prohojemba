package com.starpony.prohojemba.users.exceptions;

import com.starpony.prohojemba.errors.exceptions.ItemAlreadyExistsException;


public class UserAlreadyExistsException extends ItemAlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
