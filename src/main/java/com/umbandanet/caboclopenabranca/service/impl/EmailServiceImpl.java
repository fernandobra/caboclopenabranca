package com.umbandanet.caboclopenabranca.service.impl;

import com.umbandanet.caboclopenabranca.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("contato@umbandanet.com.br");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);



//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom("contato@umbandanet.com.br"); // Endereço válido
//            helper.setTo("destinatario@example.com");
//            helper.setSubject("Assunto");
//            helper.setText("Corpo do e-mail");
//            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage(), e);
        }
    }
}
