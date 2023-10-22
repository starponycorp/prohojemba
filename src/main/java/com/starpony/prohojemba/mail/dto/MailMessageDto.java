package com.starpony.prohojemba.mail.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MailMessageDto {
    @Email
    private final String recipient;
    @NotBlank
    private final String subject;
    @NotBlank
    private final String text;

    public MailMessageDto(String recipient, String subject, String text) {
        this.recipient = recipient;
        this.subject = subject;
        this.text = text;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
