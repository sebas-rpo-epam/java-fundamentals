package org.epamcampus.dp.behavioral.observer.bad;

import org.epamcampus.dp.behavioral.observer.impl.EmailNotificationListener;
import org.epamcampus.dp.behavioral.observer.impl.InventoryUpdateListener;
import org.epamcampus.dp.behavioral.observer.impl.AuditLogListener;
import org.epamcampus.dp.behavioral.observer.pattern.OrderPlacedEvent;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * ❌ PROBLEM: OrderService directly calls all collaborators.
 * Adding analytics tracking requires modifying OrderService.
 * Testing requires all 3 real collaborators present.
 */
public class BadOrderService {

    // ❌ Direct instantiation — tightly coupled to every listener
    private final EmailNotificationListener emailListener   = new EmailNotificationListener();
    private final InventoryUpdateListener   inventoryListener = new InventoryUpdateListener();
    private final AuditLogListener          auditListener   = new AuditLogListener();

    public UUID processOrder(String customerId, BigDecimal total) {
        UUID orderId = UUID.randomUUID();
        OrderPlacedEvent event = new OrderPlacedEvent(orderId, customerId, total, Instant.now());

        // ❌ Must call each collaborator manually — adding analytics = modifying this method
        emailListener.onEvent(event);
        inventoryListener.onEvent(event);
        auditListener.onEvent(event);

        return orderId;
    }
}
