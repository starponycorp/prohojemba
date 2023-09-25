package com.starpony.prohojemba.services;

import com.starpony.prohojemba.dto.TokensPairDto;
import com.starpony.prohojemba.dto.VerifyRequestDto;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.repositories.RefreshTokenRepository;
import com.starpony.prohojemba.repositories.VerifyCodesRepository;
import com.starpony.prohojemba.utils.JWTUtils;
import com.starpony.prohojemba.utils.VerifyCodeEmailSender;
import com.starpony.prohojemba.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final VerifyCodesRepository verifyCodesRepository;
    private final JWTUtils jwtUtils;
    private final VerifyCodeEmailSender verifyCodeEmailSender;

    @Autowired
    public AuthService(RefreshTokenRepository refreshTokenRepository, VerifyCodesRepository verifyCodesRepository, JWTUtils jwtUtils, VerifyCodeEmailSender verifyCodeEmailSender) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.verifyCodesRepository = verifyCodesRepository;
        this.jwtUtils = jwtUtils;
        this.verifyCodeEmailSender = verifyCodeEmailSender;
    }


    public Account getAuthenticatedAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public TokensPairDto createTokensPair(Account account) {
        String refreshToken = jwtUtils.generateRefreshToken(account);
        refreshTokenRepository.create(refreshToken, account.getId());
        String accessToken = jwtUtils.generateAccessToken(account);
        return new TokensPairDto(accessToken, refreshToken);
    }

    public void sendVerificationCode(VerifyRequestDto verifyRequestDto) {
        String code = VerifyCodeUtils.generate();
        verifyCodesRepository.create(verifyRequestDto.getVerifyType(), verifyRequestDto.getEmail(), code);

        verifyCodeEmailSender.send(verifyRequestDto.getEmail(), verifyRequestDto.getVerifyType(), code);
    }
}
