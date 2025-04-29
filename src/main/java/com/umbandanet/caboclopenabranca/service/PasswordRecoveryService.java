package com.umbandanet.caboclopenabranca.service;

public interface PasswordRecoveryService {
    String initiatePasswordRecovery(String email);
    void resetPassword(String token, String newPassword);
}
