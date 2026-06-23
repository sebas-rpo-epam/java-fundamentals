package org.epamcampus.polymorphism;

// Static polymorphism (overloading) — resolved at COMPILE TIME based on argument type
public class NotificationService {

    // Java picks which send() to call at compile time — this is early binding
    public void send(String message) {
        System.out.println("  [SMS/Text] " + message);
    }

    public void send(Email email) {
        System.out.println("  [Email] To: " + email.to() + " | Subject: " + email.subject());
    }

    public void send(PushNotification push) {
        System.out.println("  [Push] Device: " + push.deviceToken() + " | Body: " + push.body());
    }

    // Value objects for overloading demo
    public record Email(String to, String subject, String body) {}
    public record PushNotification(String deviceToken, String body) {}
}
