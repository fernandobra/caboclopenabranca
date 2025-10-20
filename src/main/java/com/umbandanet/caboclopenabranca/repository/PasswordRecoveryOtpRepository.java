package com.umbandanet.caboclopenabranca.repository;

import com.umbandanet.caboclopenabranca.model.PasswordRecoveryOtp;
import com.umbandanet.caboclopenabranca.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PasswordRecoveryOtpRepository extends JpaRepository<PasswordRecoveryOtp, Long> {
    PasswordRecoveryOtp findByEmailAndOtpCode(String email, String otpCode);

    @Query("SELECT  p  " +
            "FROM PasswordRecoveryOtp p " +
            "WHERE p.email = :email AND p.otpCode = :otpCode ")
    PasswordRecoveryOtp findPassawordRecovery(String email, String otpCode);;
}
