package org.epamcampus.df.jackson.datetime;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.*;

public class DateTimeExample {

    public static void run() {
        System.out.println("\n--- Jackson DateTime Handling ---");

        ShippingEvent event = new ShippingEvent(
                "SHIP-2026-001",
                LocalDate.of(2026, 6, 25),
                LocalDateTime.of(2026, 6, 18, 9, 0),
                Instant.parse("2026-06-18T08:00:00Z"),
                ZonedDateTime.of(2026, 6, 18, 10, 0, 0, 0, ZoneId.of("America/Bogota"))
        );

        // ❌ WITHOUT JavaTimeModule — Jackson 2.10+ throws InvalidDefinitionException
        System.out.println("\nWITHOUT JavaTimeModule:");
        try {
            ObjectMapper withoutModule = new ObjectMapper();
            withoutModule.enable(SerializationFeature.INDENT_OUTPUT);
            String result = withoutModule.writeValueAsString(event);
            // Only reached on very old Jackson versions (pre-2.10)
            System.out.println(result);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println("  EXCEPTION: " + e.getClass().getSimpleName());
            System.out.println("  " + (msg.contains("\n") ? msg.substring(0, msg.indexOf('\n')) : msg));
            System.out.println("  → Jackson 2.10+ requires JavaTimeModule for java.time.* types.");
            System.out.println("  → Older versions serialized LocalDate as [2026,6,18] — also wrong.");
        }

        // ✅ WITH JavaTimeModule + WRITE_DATES_AS_TIMESTAMPS=false: ISO-8601 strings
        System.out.println("\nWITH JavaTimeModule (ISO-8601):");
        try {
            ObjectMapper withModule = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .enable(SerializationFeature.INDENT_OUTPUT);
            String withIso = withModule.writeValueAsString(event);
            System.out.println(withIso);
        } catch (Exception e) {
            throw new RuntimeException("DateTime good-path failed", e);
        }
    }
}
