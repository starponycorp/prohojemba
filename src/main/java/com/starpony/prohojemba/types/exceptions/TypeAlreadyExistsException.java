package com.starpony.prohojemba.types.exceptions;

import com.starpony.prohojemba.errors.exceptions.ItemAlreadyExistsException;


public class TypeAlreadyExistsException extends ItemAlreadyExistsException {
    public TypeAlreadyExistsException() {
        super("Type already exists");
    }
}
