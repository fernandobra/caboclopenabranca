package com.umbandanet.caboclopenabranca.security;

import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class OTPGenerator {
    public static String generateOTP(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // Gera números de 0 a 9
        }
        return otp.toString();
    }
}
