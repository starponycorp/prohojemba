package com.starpony.prohojemba.models;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class Type {
    private int id;
    private String viewName;

    public Type() {};

    public Type(int id, String viewName) {
        this.id = id; this.viewName = viewName;
    };

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
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Type type)
            return this.id == type.id && this.viewName.equals(type.viewName);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, viewName);
    }
}
