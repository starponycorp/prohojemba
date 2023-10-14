package com.starpony.prohojemba.types.dto;


public class TypeViewDto {
    private int id;
    private String viewName;

    public TypeViewDto(int id, String viewName) {
        this.id = id;
        this.viewName = viewName;
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
}
