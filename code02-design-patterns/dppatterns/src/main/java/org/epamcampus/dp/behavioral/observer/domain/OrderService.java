package org.epamcampus.dp.behavioral.observer.domain;

import org.epamcampus.dp.behavioral.observer.pattern.EventPublisher;
import org.epamcampus.dp.behavioral.observer.pattern.OrderPlacedEvent;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class OrderService {

    private final EventPublisher eventPublisher; // ✅ depends only on the abstraction

    public OrderService(EventPublisher eventPublisher) {
        this.eventPublisher = Objects.requireNonNull(eventPublisher, "eventPublisher must not be null");
    }

    public OrderConfirmation placeOrder(UUID productId, int qty, String customerId, BigDecimal total) {
        // ... business logic (validate stock, calculate price, etc.) ...
        UUID orderId = UUID.randomUUID();

        // ✅ Publish event — OrderService doesn't know who handles it
        eventPublisher.publish(new OrderPlacedEvent(orderId, customerId, total, Instant.now()));

        return new OrderConfirmation(orderId, customerId, total);
    }
}
