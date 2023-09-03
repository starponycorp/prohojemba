package com.starpony.prohojemba.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class JWTAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return authentication.isAuthenticated()?authentication:null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthentication.class);
    }
}
