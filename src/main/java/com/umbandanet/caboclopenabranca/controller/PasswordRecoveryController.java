package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.dto.OtpVerificationRequest;
import com.umbandanet.caboclopenabranca.dto.PasswordRecoveryRequest;
import com.umbandanet.caboclopenabranca.model.PasswordRecoveryOtp;
import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/caboclopenabranca/password-recovery")
public class PasswordRecoveryController {

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordRecovery(@RequestBody PasswordRecoveryRequest request) {
        String token = passwordRecoveryService.initiatePasswordRecovery(request.getEmail());
        return ResponseEntity.ok("Token");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        passwordRecoveryService.resetPassword(email, newPassword);
        return ResponseEntity.ok("Sucesso.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        PasswordRecoveryOtp otp = passwordRecoveryService.verifyOtp(request.getEmail(), request.getOtp_code());
        if (otp != null && (otp.getUsed() == null || !otp.getUsed()) && otp.getExpiresAt().isAfter(LocalDateTime.now())) {
            otp.setUsed(true);
            passwordRecoveryService.save(otp);
            return ResponseEntity.ok("Sucesso.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código inválido ou expirado.");
    }

    @PostMapping
    public ResponseEntity<String> savePasswordRecoveryOtp(@RequestBody PasswordRecoveryOtp passwordRecoveryOtp) {
        passwordRecoveryService.save(passwordRecoveryOtp);
        return ResponseEntity.ok("Dados de recuperação de senha criados com sucesso.");
    }
}
