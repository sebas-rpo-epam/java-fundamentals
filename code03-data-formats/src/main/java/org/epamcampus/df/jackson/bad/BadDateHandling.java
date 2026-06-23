package org.epamcampus.df.jackson.bad;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.epamcampus.df.jackson.datetime.ShippingEvent;
import java.time.*;

public class BadDateHandling {

    public static void demonstrate() {
        System.out.println("\n--- BAD: ObjectMapper without JavaTimeModule ---");

        ShippingEvent event = new ShippingEvent(
                "SHIP-BAD-001",
                LocalDate.of(2026, 6, 25),
                LocalDateTime.of(2026, 6, 18, 9, 0),
                Instant.parse("2026-06-18T08:00:00Z"),
                ZonedDateTime.of(2026, 6, 18, 10, 0, 0, 0, ZoneId.of("America/Bogota"))
        );

        // ❌ BAD: no JavaTimeModule — throws in Jackson 2.10+
        System.out.println("WITHOUT JavaTimeModule:");
        try {
            ObjectMapper badMapper = new ObjectMapper();
            badMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String badJson = badMapper.writeValueAsString(event);
            System.out.println(badJson);
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println("  EXCEPTION: " + e.getClass().getSimpleName());
            System.out.println("  " + (msg.contains("\n") ? msg.substring(0, msg.indexOf('\n')) : msg));
        }

        // ✅ GOOD: with JavaTimeModule
        System.out.println("\nWITH JavaTimeModule:");
        try {
            ObjectMapper goodMapper = new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .enable(SerializationFeature.INDENT_OUTPUT);
            String goodJson = goodMapper.writeValueAsString(event);
            System.out.println(goodJson);
            System.out.println("-> Always register JavaTimeModule for java.time.* types.");
        } catch (Exception e) {
            throw new RuntimeException("BadDateHandling good-path failed", e);
        }
    }
}
