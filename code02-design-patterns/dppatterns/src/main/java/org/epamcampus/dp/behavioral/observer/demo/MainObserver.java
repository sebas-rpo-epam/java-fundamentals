package org.epamcampus.dp.behavioral.observer.demo;

import org.epamcampus.dp.behavioral.observer.bad.BadOrderService;
import org.epamcampus.dp.behavioral.observer.domain.OrderConfirmation;
import org.epamcampus.dp.behavioral.observer.domain.OrderService;
import org.epamcampus.dp.behavioral.observer.impl.AuditLogListener;
import org.epamcampus.dp.behavioral.observer.impl.AnalyticsListener;
import org.epamcampus.dp.behavioral.observer.impl.EmailNotificationListener;
import org.epamcampus.dp.behavioral.observer.impl.InventoryUpdateListener;
import org.epamcampus.dp.behavioral.observer.pattern.EventPublisher;
import org.epamcampus.dp.behavioral.observer.pattern.PaymentProcessedEvent;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class MainObserver {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         PATTERN 8: OBSERVER              ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n--- BAD: BadOrderService — direct coupling to all collaborators ---");
        BadOrderService bad = new BadOrderService();
        bad.processOrder("customer-001", new BigDecimal("99.99"));
        System.out.println("  (Adding analytics requires modifying BadOrderService)");

        System.out.println("\n--- GOOD: EventPublisher + OrderService ---");

        EventPublisher publisher = new EventPublisher();
        publisher.subscribe(new EmailNotificationListener());
        publisher.subscribe(new InventoryUpdateListener());
        publisher.subscribe(new AuditLogListener());

        OrderService orderService = new OrderService(publisher);

        System.out.println("\n  [Step 1] Place an order — 3 listeners fire:");
        OrderConfirmation confirmation = orderService.placeOrder(
                UUID.randomUUID(), 2, "customer-001", new BigDecimal("149.99"));
        System.out.println("  Confirmed: orderId=" + confirmation.orderId());

        System.out.println("\n  [Step 2] Process payment — only AuditLog fires (supports all events):");
        publisher.publish(new PaymentProcessedEvent(
                confirmation.orderId(), "txn-abc123", new BigDecimal("149.99"), Instant.now()));

        System.out.println("\n  [Step 3] Add AnalyticsListener — zero changes to existing code:");
        publisher.subscribe(new AnalyticsListener());

        System.out.println("\n  [Step 4] Place another order — now 4 listeners fire:");
        orderService.placeOrder(UUID.randomUUID(), 1, "customer-002", new BigDecimal("49.99"));

        System.out.println("\nKey insight: OrderService never changed. Each new listener required zero" +
                " modifications to existing code.");
        // Spring equivalent: @EventListener on any @Component method
        // Spring MVC: ApplicationEventPublisher.publishEvent() — same pattern
    }
}
