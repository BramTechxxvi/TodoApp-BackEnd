package org.bram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailService {

    @Value("${app.url}")
    private String appUrl;

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailsender) {
        this.mailSender = javaMailsender;
    }

    public String generateToken() {
        String alphabets = "ABCDEFGHIJKLMOPQRSTUVWXYZabcdefghijklmopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "@!><#%&*=?-";
        String characters = alphabets + numbers + symbols;
        StringBuilder tokenBuilder = new StringBuilder();;

        SecureRandom random = new SecureRandom();
        for (int count = 0; count < 6; count++) {
            tokenBuilder.append(characters.charAt(random.nextInt(characters.length())));
        } return tokenBuilder.toString();
    }

    public void sendVerificationEmail(String receiver, String token) {
        String subject = "Email verification";
        String verificationUrl = appUrl + "/verify?token=" + token;
        String emailContent = "Kindlu click the link below to verify your email address\n" + verificationUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom("niceibrahim01@gmail.com");
        message.setTo(receiver);
        message.setText(emailContent);

        mailSender.send(message);
        System.out.println("Email verification link sent to: " +receiver);
    }
}
