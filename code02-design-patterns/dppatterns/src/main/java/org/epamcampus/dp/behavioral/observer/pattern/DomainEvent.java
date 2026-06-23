package org.epamcampus.dp.behavioral.observer.pattern;

import java.time.Instant;

public sealed interface DomainEvent
        permits OrderPlacedEvent, PaymentProcessedEvent {
    Instant occurredAt();
    String eventType();
}
