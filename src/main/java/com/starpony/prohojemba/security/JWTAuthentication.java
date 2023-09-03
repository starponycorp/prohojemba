package com.starpony.prohojemba.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class JWTAuthentication implements Authentication {
    private final Object jwtToken;
    private final Object userId;
    private final Collection<GrantedAuthority> authorities;
    private boolean isAuthenticated;

    public JWTAuthentication(Object jwtToken, Object userId, Collection<GrantedAuthority> authorities) {
        this.jwtToken = jwtToken;
        this.userId = userId;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // Возвращает jwt токен пользователя
    @Override
    public Object getCredentials() {
        return this.jwtToken;
    }

    @Override
    public Object getDetails() {
        return this.userId;
    }

    // Возвращает id пользователя
    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}

