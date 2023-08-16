package com.starpony.prohojemba.dto;


public class ErrorDto {
    private String details;

    public ErrorDto(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }
}
