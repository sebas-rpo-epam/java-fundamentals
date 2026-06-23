package org.epamcampus.df.jackson.polymorphism;

import java.time.Instant;

public class EmailNotification extends Notification {
    private String subject;
    private String toAddress;

    public EmailNotification() {}
    public EmailNotification(String recipientId, Instant sentAt, String subject, String toAddress) {
        super(recipientId, sentAt);
        this.subject = subject;
        this.toAddress = toAddress;
    }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getToAddress() { return toAddress; }
    public void setToAddress(String toAddress) { this.toAddress = toAddress; }
}
