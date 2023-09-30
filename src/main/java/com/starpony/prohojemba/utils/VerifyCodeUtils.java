package com.starpony.prohojemba.utils;

import java.util.Random;


public class VerifyCodeUtils {
    private static int length = 6;
    private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

    public static String generate() {
        return new Random().ints(0, chars.length()).
                mapToObj(c -> chars.charAt(c)).limit(length).
                collect(StringBuffer::new, StringBuffer::append, StringBuffer::append).
                toString();
    }
}
