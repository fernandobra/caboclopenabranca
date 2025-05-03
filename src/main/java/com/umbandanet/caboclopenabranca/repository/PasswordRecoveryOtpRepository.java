package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.model.PasswordRecoveryOtp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRecoveryOtpRepository extends JpaRepository<PasswordRecoveryOtp, Long> {
    PasswordRecoveryOtp findByEmailAndOtpCode(String email, String otpCode);
}
