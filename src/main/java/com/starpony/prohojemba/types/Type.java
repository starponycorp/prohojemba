package com.starpony.prohojemba.types;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class Type {
    private int id;
    @NotBlank(message = "viewname is mandatory")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Type type) {
            return this.id == type.id && this.viewName.equals(type.viewName);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, viewName);
    }
}
