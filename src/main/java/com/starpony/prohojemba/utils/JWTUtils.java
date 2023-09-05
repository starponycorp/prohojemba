package com.starpony.prohojemba.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.starpony.prohojemba.models.Account;
import com.starpony.prohojemba.models.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;


@Component
public class JWTUtils {
    private final Algorithm accessTokenAlgorithm;
    private final Algorithm refreshTokenAlgorithm;

    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    private final JWTVerifier accessTokenVerifier;
    private final JWTVerifier refreshTokenVerifier;

    public JWTUtils(
            @Value("${jwt.access-token.secret-key}") String accessTokenSecretKey,
            @Value("${jwt.access-token.expiration}") long accessTokenExpiration,
            @Value("${jwt.refresh-token.secret-key}") String refreshTokenSecretKey,
            @Value("${jwt.refresh-token.expiration}") long refreshTokenExpiration
     ) {
        this.accessTokenAlgorithm = Algorithm.HMAC256(accessTokenSecretKey);
        this.refreshTokenAlgorithm = Algorithm.HMAC256(refreshTokenSecretKey);

        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;

        this.accessTokenVerifier = JWT.require(this.accessTokenAlgorithm)
                .withClaimPresence("userId")
                .withClaimPresence("permissions")
                .build();
        this.refreshTokenVerifier = JWT.require(this.refreshTokenAlgorithm)
                .withClaimPresence("userId")
                .withClaimPresence("permissions")
                .build();
    }

    public String generateAccessToken(Account account) {
        return generateToken(account, accessTokenAlgorithm, accessTokenExpiration);
    }

    public String generateRefreshToken(Account account) {
        return generateToken(account, refreshTokenAlgorithm, refreshTokenExpiration);
    }

    private String generateToken(Account account, Algorithm algorithm, long expiration) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withClaim("userId", account.getId())
                .withClaim("permissions",
                        account.getPermissions().stream().map(Permission::getSystemName).toList())
                .sign(algorithm);
    }

    public Optional<Account> extractAccessToken(String jwtToken) {
        return extractToken(jwtToken, accessTokenVerifier);
    }

    public Optional<Account> extractRefreshToken(String jwtToken) {
        return extractToken(jwtToken, refreshTokenVerifier);
    }

    private Optional<Account> extractToken(String jwtToken, JWTVerifier verifier) {
        try {
            DecodedJWT result = verifier.verify(jwtToken);
            return Optional.of(new Account(result.getClaim("userId").asInt(), null,
                    null, false, null, null,
                    result.getClaim("permissions").asList(String.class)
                            .stream().map(s -> new Permission(0, s, null)).toList()
            ));
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
