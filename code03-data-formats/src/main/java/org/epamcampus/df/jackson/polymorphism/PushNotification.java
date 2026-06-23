package org.epamcampus.df.jackson.polymorphism;

import java.time.Instant;

public class PushNotification extends Notification {
    private String deviceToken;
    private String title;

    public PushNotification() {}
    public PushNotification(String recipientId, Instant sentAt, String deviceToken, String title) {
        super(recipientId, sentAt);
        this.deviceToken = deviceToken;
        this.title = title;
    }

    public String getDeviceToken() { return deviceToken; }
    public void setDeviceToken(String deviceToken) { this.deviceToken = deviceToken; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
