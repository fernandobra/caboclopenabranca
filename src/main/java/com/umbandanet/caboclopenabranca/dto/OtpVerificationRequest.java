package com.umbandanet.caboclopenabranca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerificationRequest {
    private String email;
    private String otp_code;
    private LocalDateTime expires_at;
    private Boolean used;
}
