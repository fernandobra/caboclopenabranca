package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.model.Pessoas;
import com.umbandanet.caboclopenabranca.repository.PessoasRepository;
import com.umbandanet.caboclopenabranca.service.EmailService;
import com.umbandanet.caboclopenabranca.service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

    @Autowired
    private PessoasRepository pessoasRepository;

    @Autowired
    private EmailService emailService; // Serviço

    @Override
    public String initiatePasswordRecovery(String email) {
        Optional<Pessoas> pessoa = pessoasRepository.findByEmail(email);

        if (pessoa.isPresent()) {
            String token = UUID.randomUUID().toString(); // Gera um token único
            // Aqui você pode salvar o token no banco de dados associado ao usuário
            // ou em um cache como Redis, com um tempo de expiração.

            // Envia o e-mail com o token
            emailService.sendEmail(email, "Recuperação de Senha",
                    "Use este token para redefinir sua senha: " + token);

            return token;
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
}
