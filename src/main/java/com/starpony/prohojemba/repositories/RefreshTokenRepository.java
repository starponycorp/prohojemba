package com.starpony.prohojemba.repositories;

public interface RefreshTokenRepository {
    void create(String refreshToken, int accountId);

    int get(String refreshToken);

    void delete(String refreshToken);
}
