package com.starpony.prohojemba.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class RefreshTokenAuthentication extends TokenAuthentication{
    public RefreshTokenAuthentication(Object credentials, Object details, Object principal, Collection<GrantedAuthority> authorities) {
        super(credentials, details, principal, authorities);
    }
}
