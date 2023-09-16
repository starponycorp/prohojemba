package com.starpony.prohojemba.security;

import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.repositories.AccountsDatabaseRepository;
import com.starpony.prohojemba.repositories.RefreshTokenRepository;
import com.starpony.prohojemba.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountExpiredException;


@Component
public class RefreshTokenAuthProvider implements AuthenticationProvider {
    private final AccountsDatabaseRepository accountsRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JWTUtils jwtUtils;

    @Autowired
    public RefreshTokenAuthProvider(AccountsDatabaseRepository accountsRepository, RefreshTokenRepository refreshTokenRepository, JWTUtils jwtUtils) {
        this.accountsRepository = accountsRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String refreshToken = authentication.getCredentials().toString();

        Account account = jwtUtils.extractRefreshToken(refreshToken).orElseThrow(() ->
                new BadCredentialsException("Invalid refresh token"));

        int accountId = refreshTokenRepository.get(refreshToken);
        if (accountId != account.getId())
            throw new BadCredentialsException("Invalid refresh token");

        account = accountsRepository.getById(accountId).orElseThrow(() ->
               new UsernameNotFoundException(String.format("Account with id=%s not found", accountId)));

        if (account.getIsLocked()) {
            throw new DisabledException("Account is locked");
        }

        RefreshTokenAuthentication newAuthentication = new RefreshTokenAuthentication(
                null, null, account, account.getAuthorities());
        newAuthentication.setAuthenticated(true);

        return newAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(RefreshTokenAuthentication.class);
    }
}
