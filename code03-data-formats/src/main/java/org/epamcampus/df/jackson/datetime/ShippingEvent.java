package org.epamcampus.df.jackson.datetime;

import java.time.*;

public record ShippingEvent(
        String shipmentId,
        LocalDate estimatedDelivery,
        LocalDateTime scheduledAt,
        Instant dispatchedAt,
        ZonedDateTime customerNotifiedAt
) {}
