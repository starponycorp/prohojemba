package com.starpony.prohojemba.utils;

import com.starpony.prohojemba.accounts.models.Account;
import com.starpony.prohojemba.permissions.models.Permission;
import com.starpony.prohojemba.security.jwt.JWTUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class JWTUtilsTests {
    @Autowired
    private JWTUtils jwtUtils;

    @Test
    public void extractAccessToken_validToken_returnAccount() {
        Account account = new Account(1, null, null, false, null, null,
                List.of(
                        new Permission(0, "MANAGE_TITLES", null),
                        new Permission(0, "MANAGE_TYPES", null)
                ));

        String jwtToken = jwtUtils.generateAccessToken(account);

        Optional<Account> result = jwtUtils.extractAccessToken(jwtToken);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(result.get(), account);
    }

    @Test
    public void extractAccessToken_invalidToken_returnNull() {
        String invalidJwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTM4OTIwMDMsInVzZXJJZCI6MywicGVybWlzc2lvbnMiOlsiTUFOQUdFX1RJVExFUyIsIk1BTkFHRV9UWVBFUyJdfQ.aMbsb6XKM5BeSXYrtXoPKVLxeyEqKI5eOFqersap3to";

        Optional<Account> result = jwtUtils.extractAccessToken(invalidJwtToken);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void extractRefreshToken_validToken_returnAccount() {
        Account account = new Account(5, null, null, false, null, null,
                List.of(
                        new Permission(0, "MANAGE_TITLES", null)
                ));

        String jwtToken = jwtUtils.generateRefreshToken(account);

        Optional<Account> result = jwtUtils.extractRefreshToken(jwtToken);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(result.get(), account);
    }

    @Test
    public void extractRefreshToken_invalidToken_returnNull() {
        String invalidJwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTM4OTIwMDMsInVzZXJJZCI6MywicGVybWlzc2lvbnMiOlsiTUFOQUdFX1RJVExFUyIsIk1BTkFHRV9UWVBFUyJdfQ.aMbsb6XKM5BeSXYrtXoPKVLxeyEqKI5eOFqersap3to";

        Optional<Account> result = jwtUtils.extractRefreshToken(invalidJwtToken);

        Assertions.assertFalse(result.isPresent());
    }
}
