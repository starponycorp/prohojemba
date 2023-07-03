package com.starpony.prohojemba.permissions.exceptions;

import com.starpony.prohojemba.errors.exceptions.ItemAlreadyExistsException;


public class PermissionAlreadyExistsException extends ItemAlreadyExistsException {
    public PermissionAlreadyExistsException(String message) {
        super(message);
    }
}
