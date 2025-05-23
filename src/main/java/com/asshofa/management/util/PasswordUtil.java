package com.asshofa.management.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return ENCODER.encode(password);
    }

    public static boolean matchPassword(String password, String encodePassword) {
        return ENCODER.matches(password, encodePassword);
    }

}
