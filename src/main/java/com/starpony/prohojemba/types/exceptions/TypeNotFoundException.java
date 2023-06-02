package com.starpony.prohojemba.types.exceptions;

import com.starpony.prohojemba.errors.exceptions.ItemNotFoundException;


public class TypeNotFoundException extends ItemNotFoundException {
    public TypeNotFoundException(String message) {
        super(message);
    }
}
