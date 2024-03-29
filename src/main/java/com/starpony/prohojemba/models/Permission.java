package com.starpony.prohojemba.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

public class Permission implements GrantedAuthority {
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
            return this.id == permission.id &&
                    Objects.equals(this.systemName, permission.systemName) &&
                    Objects.equals(this.viewName, permission.viewName);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, systemName, viewName);
    }

    @Override
    public String getAuthority() {
        return systemName;
    }
}
