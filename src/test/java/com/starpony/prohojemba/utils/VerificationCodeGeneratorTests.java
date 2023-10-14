package com.starpony.prohojemba.utils;

import com.starpony.prohojemba.verification.utils.VerificationCodeGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class VerificationCodeGeneratorTests {
    @Test
    public void generate_returnString() {
        String randomString = VerificationCodeGenerator.generate();
        Assertions.assertNotNull(randomString);
        Assertions.assertNotEquals(randomString.length(), 0);
    }
}
