package org.epamcampus.solid.srp;

import java.math.BigDecimal;

/**
 * ❌ VIOLATES SRP — This class has AT LEAST 4 reasons to change:
 *   1. Business rules change  → modify this class
 *   2. Database schema changes → modify this class
 *   3. Email provider changes  → modify this class
 *   4. Report format changes   → modify this class
 *
 * Consequence: impossible to unit-test business logic without a DB and an SMTP server.
 */
public class BadOrderManager {

    public void processOrder(Order order) {

        // ── Responsibility 1: Business validation ──────────────────────────
        if (order.items().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        BigDecimal total = order.calculateTotal();
        if (total.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Order total must be positive");
        }

        // ── Responsibility 2: Database persistence (hardcoded SQL) ─────────
        // In real code this would open a real JDBC connection — untestable!
        System.out.println("  [BadOrderManager-DB] INSERT INTO orders VALUES ('"
                + order.id() + "', " + total + ")");

        // ── Responsibility 3: Email notification (hardcoded SMTP) ──────────
        System.out.println("  [BadOrderManager-Email] SMTP → " + order.customerEmail()
                + " | Subject: Order Confirmation");

        // ── Responsibility 4: Report generation (hardcoded file path) ──────
        System.out.println("  [BadOrderManager-Report] Writing report_" + order.id() + ".txt");
    }
}
