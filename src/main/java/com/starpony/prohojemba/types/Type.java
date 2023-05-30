package com.starpony.prohojemba.types;

import jakarta.validation.constraints.NotBlank;

public class Type {
    private int id;
    @NotBlank(message = "Viewname is mandatory")
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
