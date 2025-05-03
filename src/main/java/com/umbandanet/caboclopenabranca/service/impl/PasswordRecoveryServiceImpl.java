package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.model.PasswordRecoveryOtp;
import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.repository.PasswordRecoveryOtpRepository;
import com.umbandanet.caboclopenabranca.repository.PessoasRepository;
import com.umbandanet.caboclopenabranca.security.OTPGenerator;
import com.umbandanet.caboclopenabranca.service.EmailService;
import com.umbandanet.caboclopenabranca.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

    @Autowired
    private PessoasRepository pessoasRepository;

    @Autowired
    private PasswordRecoveryOtpRepository passwordRecoveryOtpRepository; // Repositório de OTP

    @Autowired
    private EmailService emailService; // Serviço

    @Autowired
    private OTPGenerator otpGenerator; // Gerador de OTP

    @Override
    public String initiatePasswordRecovery(String email) {
        Optional<Pessoas> pessoa = pessoasRepository.findByEmail(email);

        if (pessoa.isPresent()) {
         //   String token = UUID.randomUUID().toString(); // Gera um token único
            String otp = OTPGenerator.generateOTP(6); // Gera um OTP de 6 dígitos
            // Aqui você pode salvar o token no banco de dados associado ao usuário
            // ou em um cache como Redis, com um tempo de expiração.

            // Salvar o OTP no banco de dados
            PasswordRecoveryOtp passwordRecoveryOtp = new PasswordRecoveryOtp();
            passwordRecoveryOtp.setEmail(email);
            passwordRecoveryOtp.setOtpCode(otp);
            LocalDateTime agora = LocalDateTime.now();
            passwordRecoveryOtp.setExpiresAt(agora);

            this.save(passwordRecoveryOtp); // Salva o OTP no banco de dados

            // Envia o e-mail com o token
            this.emailService.sendEmail(email, "Código de Verificação",
                    " Seu código é: " + otp);

            return otp;
        } else {
            throw new IllegalArgumentException("E-mail não encontrado.");
        }
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        // Aqui você deve validar o token (ex.: buscar no banco ou cache)
        // e associá-lo ao usuário correto.

        // Após validar o token, atualize a senha do usuário:
        Optional<Pessoas> pessoa = pessoasRepository.findByToken(token); // Exemplo
        if (pessoa.isPresent()) {
            Pessoas user = pessoa.get();
            user.setSenha(newPassword); // Atualize a senha
            pessoasRepository.save(user); // Salve no banco
        } else {
            throw new IllegalArgumentException("Token inválido ou expirado.");
        }
    }

    @Override
    public PasswordRecoveryOtp verifyOtp(String email, String otpCode) {
        return passwordRecoveryOtpRepository
                .findByEmailAndOtpCode(email, otpCode);
    }

    @Transactional
    @Override
    public void save(PasswordRecoveryOtp otpParmeter) {
        if(otpParmeter.getId() == null) {
            otpParmeter.setId(0L);
        }

        Optional<PasswordRecoveryOtp> existingOtp = passwordRecoveryOtpRepository.findById(otpParmeter.getId());
        if (existingOtp.isPresent()) {
            // Atualize a entidade existente
            PasswordRecoveryOtp otp = existingOtp.get();
            otp.setOtpCode(otpParmeter.getOtpCode());
            otp.setExpiresAt(LocalDateTime.now());
            otp.setEmail(otpParmeter.getEmail());
            passwordRecoveryOtpRepository.save(otp);
        } else {
            // Crie uma nova entidade
            PasswordRecoveryOtp newOtp = new PasswordRecoveryOtp();
            newOtp.setEmail(otpParmeter.getEmail());
            newOtp.setOtpCode(otpParmeter.getOtpCode());
            newOtp.setExpiresAt(LocalDateTime.now());
            passwordRecoveryOtpRepository.save(newOtp);
        }
    }
}
