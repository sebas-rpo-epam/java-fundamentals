package org.epamcampus.df.jackson.polymorphism;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.Instant;

public class PolymorphismExample {

    public static void run() {
        System.out.println("\n--- Jackson Polymorphism ---");

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);

        try {
            Notification email = new EmailNotification("user-1", Instant.parse("2026-06-18T10:00:00Z"),
                    "Your order shipped", "alice@epam.com");
            Notification sms = new SmsNotification("user-2", Instant.parse("2026-06-18T10:01:00Z"),
                    "+573001234567");
            Notification push = new PushNotification("user-3", Instant.parse("2026-06-18T10:02:00Z"),
                    "device-token-xyz", "Package delivered!");

            // Serialize each as base type — shows "type" discriminator field
            String emailJson = mapper.writeValueAsString(email);
            String smsJson = mapper.writeValueAsString(sms);
            String pushJson = mapper.writeValueAsString(push);

            System.out.println("EmailNotification JSON:");
            System.out.println(emailJson);
            System.out.println("SmsNotification JSON:");
            System.out.println(smsJson);
            System.out.println("PushNotification JSON:");
            System.out.println(pushJson);

            // Deserialize each JSON string as base type Notification
            Notification parsedEmail = mapper.readValue(emailJson, Notification.class);
            Notification parsedSms = mapper.readValue(smsJson, Notification.class);
            Notification parsedPush = mapper.readValue(pushJson, Notification.class);

            System.out.println("Deserialized types:");
            System.out.println("  " + parsedEmail.getClass().getSimpleName() + " for recipient " + parsedEmail.getRecipientId());
            System.out.println("  " + parsedSms.getClass().getSimpleName() + " for recipient " + parsedSms.getRecipientId());
            System.out.println("  " + parsedPush.getClass().getSimpleName() + " for recipient " + parsedPush.getRecipientId());

            System.out.println("Jackson reads the 'type' field and creates the correct subtype automatically.");

        } catch (Exception e) {
            throw new RuntimeException("Polymorphism example failed", e);
        }
    }
}
