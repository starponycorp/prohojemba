package com.starpony.prohojemba.security.token.refresh;

import com.starpony.prohojemba.security.token.TokenAuthentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class RefreshTokenAuthentication extends TokenAuthentication {
    public RefreshTokenAuthentication(Object credentials, Object details, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(credentials, details, principal, authorities);
    }
}
