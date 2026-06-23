package org.epamcampus.dp.behavioral.observer.pattern;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderPlacedEvent(
        UUID orderId,
        String customerId,
        BigDecimal total,
        Instant occurredAt
) implements DomainEvent {

    @Override
    public String eventType() { return "ORDER_PLACED"; }
}
