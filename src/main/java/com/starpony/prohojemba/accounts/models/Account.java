package com.starpony.prohojemba.accounts.models;

import com.starpony.prohojemba.permissions.models.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class Account implements UserDetails {
    private int id;
    private String email;
    private String encodedPassword;
    private boolean isLocked;

    private String username;
    private String avatar;

    private List<Permission> permissions;

    public Account() {}

    public Account(int id, String email, String encodedPassword, boolean isLocked, String username, String avatar, List<Permission> permissions) {
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

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean locked) {
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getPermissions();
    }

    @Override
    public String getPassword() {
        return getEncodedPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !getIsLocked();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getIsLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !getIsLocked();
    }

    @Override
    public boolean isEnabled() {
        return !getIsLocked();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if (obj instanceof Account account)
            return this.id == account.id &&
                    Objects.equals(this.email, account.email) &&
                    Objects.equals(this.encodedPassword, account.encodedPassword) &&
                    this.isLocked == account.isLocked &&
                    Objects.equals(this.username, account.username) &&
                    Objects.equals(this.avatar, account.avatar) &&
                    Objects.equals(this.getPermissions(), account.getPermissions());

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
