package com.starpony.prohojemba.types.exceptions;

import com.starpony.prohojemba.errors.exceptions.ItemNotFoundException;


public class TypeNotFoundException extends ItemNotFoundException {
    public TypeNotFoundException(int id) {
        super(String.format("Type with id=%d not found", id));
    }
}
