package com.umbandanet.caboclopenabranca.controller;

import com.umbandanet.caboclopenabranca.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/caboclopenabranca/auth")
public class AuthController {

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody String expiredToken) {
        String username = JwtUtil.validateExpiredToken(expiredToken);
        if (username != null) {
            String newToken = JwtUtil.generateToken(username);
            return ResponseEntity.ok(newToken);
        } else {
            return ResponseEntity.status(401).body("Token inválido ou não pode ser renovado.");
        }
    }
}
