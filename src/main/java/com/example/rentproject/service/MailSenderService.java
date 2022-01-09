package com.example.rentproject.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private final JavaMailSender emailSender;

    public MailSenderService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendRegistrationLetter(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        this.emailSender.send(mailMessage);
    }

    public void sendNewsLetter(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        this.emailSender.send(mailMessage);
    }
}
