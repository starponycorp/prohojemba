package com.starpony.prohojemba.models;

import java.util.Objects;

public class Permission {
    private int id;
    private String systemName;
    private String viewName;

    public Permission(){}

    public Permission(int id, String systemName, String viewName) {
        this.id = id;this.systemName = systemName;this.viewName = viewName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
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

        if (obj instanceof Permission permission)
            return this.id == permission.id && this.systemName.equals(permission.systemName) && this.viewName.equals(permission.viewName);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, systemName, viewName);
    }
}
