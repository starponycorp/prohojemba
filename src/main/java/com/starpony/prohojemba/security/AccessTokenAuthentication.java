package com.starpony.prohojemba.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class AccessTokenAuthentication extends TokenAuthentication{
    public AccessTokenAuthentication(Object credentials, Object details, Object principal, Collection<GrantedAuthority> authorities) {
        super(credentials, details, principal, authorities);
    }
}
