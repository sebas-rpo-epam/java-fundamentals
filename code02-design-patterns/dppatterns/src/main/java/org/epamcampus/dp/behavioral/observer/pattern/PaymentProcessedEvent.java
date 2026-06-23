package org.epamcampus.dp.behavioral.observer.pattern;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentProcessedEvent(
        UUID orderId,
        String transactionId,
        BigDecimal amount,
        Instant occurredAt
) implements DomainEvent {

    @Override
    public String eventType() { return "PAYMENT_PROCESSED"; }
}
