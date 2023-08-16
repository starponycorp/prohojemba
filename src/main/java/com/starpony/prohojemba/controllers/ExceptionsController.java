package com.starpony.prohojemba.controllers;

import com.starpony.prohojemba.dto.ErrorDto;
import com.starpony.prohojemba.exceptions.ItemAlreadyExistsException;
import com.starpony.prohojemba.exceptions.ItemFormException;
import com.starpony.prohojemba.exceptions.ItemNotFoundException;

import jakarta.validation.ConstraintViolationException;
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

    @ExceptionHandler(value = {ConstraintViolationException.class, ItemFormException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleDtoValidationError(ConstraintViolationException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ExceptionHandler(value = {ItemAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorDto handleItemAlreadyExists(ItemAlreadyExistsException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
