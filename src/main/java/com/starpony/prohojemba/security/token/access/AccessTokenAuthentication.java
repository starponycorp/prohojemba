package com.starpony.prohojemba.security.token.access;

import com.starpony.prohojemba.security.token.TokenAuthentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class AccessTokenAuthentication extends TokenAuthentication {
    public AccessTokenAuthentication(Object credentials, Object details, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(credentials, details, principal, authorities);
    }
}
