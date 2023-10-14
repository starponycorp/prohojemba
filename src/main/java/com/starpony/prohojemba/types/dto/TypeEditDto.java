package com.starpony.prohojemba.types.dto;

import jakarta.validation.constraints.NotBlank;


public class TypeEditDto {
    @NotBlank
    private String viewName;

    public TypeEditDto(String viewName) {
        this.viewName = viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

}
