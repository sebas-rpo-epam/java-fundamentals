package org.epamcampus.solid.srp;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MainSrp {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       SOLID — S: Single Responsibility   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Order order = new Order(
                UUID.randomUUID(),
                "student@epam.com",
                List.of(
                        new Item("Java Course", new BigDecimal("199.99"), 1),
                        new Item("IDE License", new BigDecimal("49.99"),  2)
                )
        );

        // ─── BAD: one class, four responsibilities ─────────────────────────
        System.out.println("\n--- BAD: BadOrderManager (4 reasons to change) ---");
        System.out.println("  Problem: you cannot test business logic without DB + SMTP!");
        new BadOrderManager().processOrder(order);

        // ─── GOOD: each class has one responsibility ───────────────────────
        System.out.println("\n--- GOOD: OrderProcessor + collaborators (each owns one job) ---");
        OrderProcessor processor = new OrderProcessor(
                new InMemoryOrderRepository(),
                new ConsoleNotificationService(),
                new ConsoleReportService()
        );
        processor.process(order);

        System.out.println("\nKey insight: to swap email provider → change ONLY ConsoleNotificationService.");
        System.out.println("  OrderProcessor, DB, and Report code remain untouched.");
    }
}
