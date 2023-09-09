package com.starpony.prohojemba.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class JWTAuthentication implements Authentication {
    private final Object jwtToken;
    private final Object accountId;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;

    public JWTAuthentication(Object jwtToken, Object accountId, Collection<? extends GrantedAuthority> authorities) {
        this.jwtToken = jwtToken;
        this.accountId = accountId;
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
        return this.accountId;
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
