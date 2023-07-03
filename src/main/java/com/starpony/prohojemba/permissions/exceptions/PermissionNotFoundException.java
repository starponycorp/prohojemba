package com.starpony.prohojemba.permissions.exceptions;

import com.starpony.prohojemba.errors.exceptions.ItemNotFoundException;


public class PermissionNotFoundException extends ItemNotFoundException {
    public PermissionNotFoundException(String message) {
        super(message);
    }
}
