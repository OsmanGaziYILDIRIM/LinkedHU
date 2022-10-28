package com.powerrangers.linkedhu.service;

import com.sun.mail.smtp.SMTPAddressFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailBase;

    public void sendEmail(String to,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailBase);
        message.setTo(to);
        message.setText(body);
        message.setSubject(subject);

        try {
            mailSender.send(message);
            System.out.println("Mail susccessfully sent to: "+to);
        }
        catch (Exception e) {
            System.out.println("Mail can't sending to: "+to);
        }

    }
}
