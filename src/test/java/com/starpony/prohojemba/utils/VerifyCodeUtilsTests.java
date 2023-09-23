package com.starpony.prohojemba.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class VerifyCodeUtilsTests {
    @Test
    public void generate_returnString() {
        String randomString = VerifyCodeUtils.generate();
        Assertions.assertNotNull(randomString);
        Assertions.assertNotEquals(randomString.length(), 0);
    }
}
