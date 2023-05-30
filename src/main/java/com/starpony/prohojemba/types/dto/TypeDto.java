package com.starpony.prohojemba.types.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.starpony.prohojemba.types.Type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class TypeDto {
    private int id;
    private String viewName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
