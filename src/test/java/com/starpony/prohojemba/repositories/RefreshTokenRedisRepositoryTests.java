package com.starpony.prohojemba.repositories;

import com.starpony.prohojemba.security.token.refresh.repositories.impl.RefreshTokenRedisRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class RefreshTokenRedisRepositoryTests {
    @Autowired
    private RefreshTokenRedisRepository tokenRepository;

    @Test
    public void create_refreshTokenNull_throwException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> tokenRepository.create(null, 1));
    }

    @Test
    public void get_refreshTokenExist_returnAccountId() {
        String refreshToken = "test.refresh.token";
        int expectedResult = 1;

        tokenRepository.create(refreshToken, expectedResult);

        int result = tokenRepository.get(refreshToken);

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void get_refreshTokenNotExist_return0() {
        int expectedResult = 0;

        int result = tokenRepository.get("not-existed-key");

        Assertions.assertEquals(result, expectedResult);
    }

    @Test
    public void delete_refreshTokenExist_correctDelete() {
        int expectedResult = 0;

        tokenRepository.create("test.refresh.token", 1);

        tokenRepository.delete("test.refresh.token");

        int result = tokenRepository.get("test.refresh.token");

        Assertions.assertEquals(result, expectedResult);
    }
}
