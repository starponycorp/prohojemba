package com.starpony.prohojemba.repositories;

public interface RefreshTokenRepository {
    void create(String refreshToken, int accountId);

    void delete(String refreshToken);
}
