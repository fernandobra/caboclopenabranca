package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.dto.PasswordRecoveryRequest;
import com.umbandanet.caboclopenabranca.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/caboclopenabranca/password-recovery")
public class PasswordRecoveryController {

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordRecovery(@RequestBody PasswordRecoveryRequest request) {
        String token = passwordRecoveryService.initiatePasswordRecovery(request.getEmail());
        return ResponseEntity.ok("Token enviado para o e-mail: " + request.getEmail());
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        passwordRecoveryService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }
}
