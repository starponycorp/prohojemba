package com.starpony.prohojemba.security.token.access;

import com.starpony.prohojemba.security.token.access.AccessTokenAuthentication;
import com.starpony.prohojemba.security.jwt.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class AccessTokenAuthProvider implements AuthenticationProvider {
    private final JWTUtils jwtUtils;

    @Autowired
    public AccessTokenAuthProvider(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String accessToken = authentication.getCredentials().toString();

        UserDetails userDetails = jwtUtils.extractAccessToken(accessToken)
                .orElseThrow(() -> new BadCredentialsException("Invalid access token"));

        Authentication newAuthentication = new AccessTokenAuthentication(
                null, authentication.getDetails(), userDetails,
                userDetails.getAuthorities()
        );
        newAuthentication.setAuthenticated(true);
        return newAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AccessTokenAuthentication.class);
    }
}
