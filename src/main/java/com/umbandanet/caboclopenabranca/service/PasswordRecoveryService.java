package com.umbandanet.caboclopenabranca.service;

import com.umbandanet.caboclopenabranca.dto.OtpVerificationRequest;
import com.umbandanet.caboclopenabranca.model.PasswordRecoveryOtp;

public interface PasswordRecoveryService {
    String initiatePasswordRecovery(String email);
    void resetPassword(String token, String newPassword);
    PasswordRecoveryOtp verifyOtp(String email, String otpCode);
    void save(PasswordRecoveryOtp otp);
}
