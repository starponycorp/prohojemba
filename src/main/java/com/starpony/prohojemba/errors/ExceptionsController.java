package com.starpony.prohojemba.errors;

import com.starpony.prohojemba.errors.exceptions.ItemAlreadyExistsException;
import com.starpony.prohojemba.errors.exceptions.ItemNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(value = {ItemNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleItemNotFound(ItemNotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(value = {ItemAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleItemAlreadyExists(ItemAlreadyExistsException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
