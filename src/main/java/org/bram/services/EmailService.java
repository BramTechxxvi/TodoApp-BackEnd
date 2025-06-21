package org.bram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class EmailService {

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
            tokenBuilder.append(characters.charAt(random.nextInt(alphabets.length())));
        } return tokenBuilder.toString();
    }

    public void sendVerificationEmail(String receiver, String token) {
        String subject = "Email verification";
        String verificationUrl = "http://localhost:8080/verify?token=";
    }
}
