package com.starpony.prohojemba.titles.exceptions;

import com.starpony.prohojemba.errors.exceptions.ItemNotFoundException;


public class TitleNotFoundException extends ItemNotFoundException {
    public TitleNotFoundException(String message) {
        super(message);
    }
}
