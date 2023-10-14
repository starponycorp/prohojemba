package com.starpony.prohojemba.mail.dto;


public class MailMessageDto {
    private final String recipient;
    private final String subject;
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
