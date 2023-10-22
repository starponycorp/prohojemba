package com.starpony.prohojemba.security;

import com.starpony.prohojemba.dto.LoginDto;
import com.starpony.prohojemba.dto.RefreshTokenDto;
import com.starpony.prohojemba.dto.TokensPairDto;
import com.starpony.prohojemba.dto.VerifyRequestDto;
import com.starpony.prohojemba.accounts.models.Account;
import com.starpony.prohojemba.security.token.refresh.RefreshTokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerNewUser() {}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokensPairDto loginInExistingUser(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        Account account = (Account) authentication.getPrincipal();

        return authService.createTokensPair(account);
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public TokensPairDto updateAuthTokensPair(@RequestBody RefreshTokenDto tokenDto) {
        RefreshTokenAuthentication refreshTokenAuthentication =
                new RefreshTokenAuthentication(tokenDto.getRefreshToken(), null, null, null);

        Authentication authentication = authenticationManager.authenticate(refreshTokenAuthentication);
        Account account = (Account) authentication.getPrincipal();

        return authService.createTokensPair(account);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public void requestToVerifyByCode(@RequestBody VerifyRequestDto verifyRequestDto) {
        authService.sendVerificationCode(verifyRequestDto);
    }
}
