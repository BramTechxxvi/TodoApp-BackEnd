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

        SecureRandom random = new vSecureRandom();
        for (in c)
    }
}
