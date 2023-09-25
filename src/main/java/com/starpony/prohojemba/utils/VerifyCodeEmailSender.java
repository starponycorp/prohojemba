package com.starpony.prohojemba.utils;

import com.starpony.prohojemba.enums.VerifyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class VerifyCodeEmailSender {
    private final JavaMailSender mailSender;

    @Autowired
    public VerifyCodeEmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String recipient, VerifyType verifyType, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ezzik98@yandex.ru");
        message.setTo(recipient);
        message.setSubject("Код верификации");
        message.setText(String.format("Код верификации для регистрации на ресурсе: %s", code));
        mailSender.send(message);
    }
}
