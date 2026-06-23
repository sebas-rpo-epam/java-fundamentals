package org.epamcampus.df.jackson.polymorphism;

import java.time.Instant;

public class SmsNotification extends Notification {
    private String phoneNumber;

    public SmsNotification() {}
    public SmsNotification(String recipientId, Instant sentAt, String phoneNumber) {
        super(recipientId, sentAt);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
