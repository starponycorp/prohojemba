package com.starpony.prohojemba.controllers;

import com.starpony.prohojemba.dto.LoginDto;
import com.starpony.prohojemba.dto.TokensPairDto;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.repositories.RefreshTokenRepository;
import com.starpony.prohojemba.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder, JWTUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerNewUser() {}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokensPairDto loginInExistingUser(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        String password = passwordEncoder.encode("testpassword");

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        Account account = (Account) authentication.getPrincipal();

        String refreshToken = jwtUtils.generateRefreshToken(account);
        refreshTokenRepository.create(refreshToken, account.getId());
        String accessToken = jwtUtils.generateAccessToken(account);
        return new TokensPairDto(accessToken, refreshToken);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public void updateAuthTokensPair() {}
}
