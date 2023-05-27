package com.starpony.prohojemba.types.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.starpony.prohojemba.types.Type;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class TypeDto {
    private int id;

    @NotBlank(message = "Viewname is mandatory")
    private String viewName;

    public TypeDto() {};

    public TypeDto(Type type) {
        this.id = type.getId();
        this.viewName = type.getViewName();
    }

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

    public Type toType() {
        return new Type(this.viewName);
    }
}
