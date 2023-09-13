package com.starpony.prohojemba.services;

import com.starpony.prohojemba.models.Account;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    public Account getAuthenticatedAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
