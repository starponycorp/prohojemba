package com.starpony.prohojemba.services;

import com.starpony.prohojemba.dto.LoginDto;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.repositories.AccountsDatabaseRepository;
import com.starpony.prohojemba.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final AccountsDatabaseRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    @Autowired
    public AuthService(AccountsDatabaseRepository accountsRepository, PasswordEncoder passwordEncoder, JWTUtils jwtUtils) {
        this.accountsRepository = accountsRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public Account getAuthenticatedAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void authenticateAccount(LoginDto loginDto) {
    }

    public void refreshTokens() {

    }
}
