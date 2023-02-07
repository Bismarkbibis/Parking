package com.example.Parking.outils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig implements PasswordEncoder{

            private BCryptPasswordEncoder bCryptPasswordEncoder;
            private  String regexPhoneNumber= "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";

            @Override
            public String encode(CharSequence rawPassword) {
             return bCryptPasswordEncoder.encode(rawPassword.toString());
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
              return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
            }

            public boolean verifyEmailFormat(String email){
                return regexPhoneNumber.matches(email);
            }

}
