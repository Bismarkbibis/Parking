package com.example.Parking.outils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Config  {

    private static final String regexEmail = "^[\\w-.]+@[\\w-]+\\.[\\w]{2,}$";

    public Config() {

    }

    public static boolean verifyEmailFormat(String email) {
        return email.matches(regexEmail);
    }

    public static boolean matches(String password, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, hashedPassword);
    }
    public static String encode(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }


}
