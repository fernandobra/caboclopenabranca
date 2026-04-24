package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.dto.OtpVerificationRequest;
import com.umbandanet.caboclopenabranca.dto.PasswordRecoveryRequest;
import com.umbandanet.caboclopenabranca.model.PasswordRecoveryOtp;
import com.umbandanet.caboclopenabranca.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/caboclopenabranca/password-recovery")
public class PasswordRecoveryController {

    private static final Logger logger = LoggerFactory.getLogger(PasswordRecoveryController.class);

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordRecovery(@RequestBody PasswordRecoveryRequest request) {
        logger.info("Password recovery request received for email={}", request.getEmail());
        String token = passwordRecoveryService.initiatePasswordRecovery(request.getEmail());
        logger.debug("Generated OTP for {}: {}", request.getEmail(), token);
        // retorna o código/OTP gerado (ou mensagem genérica em produção)
        return ResponseEntity.ok(token);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        passwordRecoveryService.resetPassword(email, newPassword);
        return ResponseEntity.ok("Sucesso.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        return Optional.ofNullable(passwordRecoveryService.verifyOtp(request.getEmail(), request.getOtp_code()))
                .filter(otp -> (otp.getUsed() == null || !otp.getUsed()) && otp.getExpiresAt().isAfter(LocalDateTime.now()))
                .map(otp -> {
                    otp.setUsed(true);
                    passwordRecoveryService.save(otp);
                    return ResponseEntity.ok("Sucesso.");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código inválido ou expirado."));
    }

    @PostMapping
    public ResponseEntity<String> savePasswordRecoveryOtp(@RequestBody PasswordRecoveryOtp passwordRecoveryOtp) {
        passwordRecoveryService.save(passwordRecoveryOtp);
        return ResponseEntity.ok("Dados de recuperação de senha criados com sucesso.");
    }
}