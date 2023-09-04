package com.starpony.prohojemba.security;

import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class JWTAuthProvider implements AuthenticationProvider {
    private final JWTUtils jwtUtils;

    @Autowired
    public JWTAuthProvider(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwtToken = (String) authentication.getCredentials();

        Account account 

        JWTAuthentication newAuthentication = new JWTAuthentication();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JWTAuthentication.class);
    }
}
