package com.starpony.prohojemba.models;

import com.starpony.prohojemba.models.Permission;

import java.util.List;
import java.util.Objects;


public class User {
    private int id;
    private String email;
    private String encodedPassword;
    private boolean isLocked;

    private String username;
    private String avatar;

    private List<Permission> permissions;

    public User() {}

    public User(int id, String email, String encodedPassword, boolean isLocked, String username, String avatar, List<Permission> permissions) {
        this.id = id;this.email = email;this.encodedPassword = encodedPassword;this.isLocked = isLocked;
        this.username = username;this.avatar = avatar;this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (obj instanceof User user)
            return this.id == user.id &&
                    Objects.equals(this.email, user.email) &&
                    Objects.equals(this.encodedPassword, user.encodedPassword) &&
                    this.isLocked == user.isLocked &&
                    Objects.equals(this.username, user.username) &&
                    Objects.equals(this.avatar, user.avatar) &&
                    Objects.equals(this.permissions, user.permissions);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
