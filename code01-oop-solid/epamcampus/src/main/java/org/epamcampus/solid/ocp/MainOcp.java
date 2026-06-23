package org.epamcampus.solid.ocp;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MainOcp {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       SOLID — O: Open/Closed             ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Order order = new Order(UUID.randomUUID(), new BigDecimal("200.00"));

        // ─── BAD: switch-on-string — must modify to add new types ──────────
        System.out.println("\n--- BAD: BadDiscountCalculator (modify to extend) ---");
        BadDiscountCalculator bad = new BadDiscountCalculator();
        System.out.println("  STUDENT  discount: $" + bad.calculate(order, "STUDENT"));
        System.out.println("  SENIOR   discount: $" + bad.calculate(order, "SENIOR"));
        System.out.println("  UNKNOWN  discount: $" + bad.calculate(order, "UNKNOWN"));
        System.out.println("  → Adding LOYALTY requires editing this file (risky!)");

        // ─── GOOD: new policy = new class, DiscountEngine unchanged ────────
        System.out.println("\n--- GOOD: DiscountEngine (extend without modifying) ---");

        DiscountEngine engine = new DiscountEngine(List.of(
                new StudentDiscountPolicy(),
                new SeniorDiscountPolicy(),
                new LoyaltyDiscountPolicy(),
                new BlackFridayDiscountPolicy()  // added without touching engine!
        ));

        Customer student = new Customer("c1", "Alice", CustomerType.STUDENT, 3);
        Customer loyal   = new Customer("c2", "Bob",   CustomerType.STANDARD, 15);
        Customer newUser = new Customer("c3", "Carol", CustomerType.STANDARD, 2);

        System.out.println("\nAlice (STUDENT, 3 purchases) — $200 order:");
        BigDecimal d1 = engine.calculateBestDiscount(order, student);
        System.out.println("  Best discount: $" + d1 + " → Final: $" + order.total().subtract(d1));

        System.out.println("\nBob (STANDARD, 15 purchases) — $200 order:");
        BigDecimal d2 = engine.calculateBestDiscount(order, loyal);
        System.out.println("  Best discount: $" + d2 + " → Final: $" + order.total().subtract(d2));

        System.out.println("\nCarol (STANDARD, 2 purchases) — $200 order:");
        BigDecimal d3 = engine.calculateBestDiscount(order, newUser);
        System.out.println("  Best discount: $" + d3 + " (no policy applies)");
    }
}
