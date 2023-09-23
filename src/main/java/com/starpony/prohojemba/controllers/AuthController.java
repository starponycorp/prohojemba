package com.starpony.prohojemba.controllers;

import com.starpony.prohojemba.dto.LoginDto;
import com.starpony.prohojemba.dto.RefreshTokenDto;
import com.starpony.prohojemba.dto.TokensPairDto;
import com.starpony.prohojemba.dto.VerifyRequestDto;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.repositories.RefreshTokenRepository;
import com.starpony.prohojemba.security.RefreshTokenAuthentication;
import com.starpony.prohojemba.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


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

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        Account account = (Account) authentication.getPrincipal();

        return createTokensPair(account);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public TokensPairDto updateAuthTokensPair(@RequestBody RefreshTokenDto tokenDto) {
        RefreshTokenAuthentication refreshTokenAuthentication =
                new RefreshTokenAuthentication(tokenDto.getRefreshToken(), null, null, null);

        Authentication authentication = authenticationManager.authenticate(refreshTokenAuthentication);
        Account account = (Account) authentication.getPrincipal();

        return createTokensPair(account);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public void requestToVerifyByCode(@RequestBody VerifyRequestDto verifyRequestDto) {
        
    }

    private TokensPairDto createTokensPair(Account account) {
        String refreshToken = jwtUtils.generateRefreshToken(account);
        refreshTokenRepository.create(refreshToken, account.getId());
        String accessToken = jwtUtils.generateAccessToken(account);
        return new TokensPairDto(accessToken, refreshToken);
    }
}
