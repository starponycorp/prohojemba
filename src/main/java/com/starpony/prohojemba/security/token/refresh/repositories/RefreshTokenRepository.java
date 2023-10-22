package com.starpony.prohojemba.security.token.refresh.repositories;

public interface RefreshTokenRepository {
    void create(String refreshToken, int accountId);

    int get(String refreshToken);

    void delete(String refreshToken);
}
