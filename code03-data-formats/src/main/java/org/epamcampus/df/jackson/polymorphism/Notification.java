package org.epamcampus.df.jackson.polymorphism;

import com.fasterxml.jackson.annotation.*;
import java.time.Instant;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailNotification.class, name = "EMAIL"),
        @JsonSubTypes.Type(value = SmsNotification.class, name = "SMS"),
        @JsonSubTypes.Type(value = PushNotification.class, name = "PUSH")
})
public abstract class Notification {
    private String recipientId;
    private Instant sentAt;

    protected Notification() {}
    protected Notification(String recipientId, Instant sentAt) {
        this.recipientId = recipientId;
        this.sentAt = sentAt;
    }

    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
    public Instant getSentAt() { return sentAt; }
    public void setSentAt(Instant sentAt) { this.sentAt = sentAt; }
}
