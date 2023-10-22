package com.starpony.prohojemba.mail;

import com.starpony.prohojemba.mail.dto.MailMessageDto;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Properties;


@Service
@Validated
public class MailService {
    private final Session session;
    private final String from;

    public MailService(
        @Value("${email.host}") String host,
        @Value("${email.port}") int port,
        @Value("${email.username}") String username,
        @Value("${email.password}") String password,
        @Value("${email.ssl.enable}") String enableSsl,
        @Value("${email.tls.enable}") String enableTls
    ) {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.ssl.enable", enableSsl);
        properties.setProperty("mail.smtp.starttls.enable", enableTls);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

        from = username;
        session = Session.getInstance(properties, authenticator);
    }

    public void send(@Valid MailMessageDto mailMessageDto) throws ValidationException {
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailMessageDto.getRecipient()));
            mimeMessage.setSubject(mailMessageDto.getSubject());
            mimeMessage.setText(mailMessageDto.getText());
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
