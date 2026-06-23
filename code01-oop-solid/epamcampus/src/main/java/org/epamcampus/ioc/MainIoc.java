package org.epamcampus.ioc;

import java.math.BigDecimal;
import java.util.UUID;

public class MainIoc {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║      IoC / DEPENDENCY INJECTION          ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ─── Manual wiring (what Spring's IoC container does automatically) ─
        System.out.println("\n--- Wiring the object graph (simulating Spring's IoC container) ---");

        UUID javaBookId = UUID.randomUUID();
        Product javaBook = new Product(javaBookId, "Effective Java 4th Ed.",
                new BigDecimal("49.99"), 100);

        // Low-level impls
        InMemoryProductRepository repo      = new InMemoryProductRepository(javaBook);
        CustomerTierPricingStrategy pricing  = new CustomerTierPricingStrategy();
        SimpleInventoryService inventory     = new SimpleInventoryService(javaBook);

        // High-level service receives them via constructor — it never calls new() itself
        // In Spring: @Service + @Repository + @Component → container does this wiring
        ProductOrderService service = new ProductOrderService(repo, pricing, inventory);

        System.out.println("All dependencies wired. ProductOrderService ready.\n");

        // ─── Place orders for different customer tiers ──────────────────────
        Customer premium  = Customer.premium("Alice (PREMIUM)");
        Customer standard = Customer.standard("Bob (STANDARD)");
        Customer guest    = Customer.guest("Carol (GUEST)");

        System.out.println("Alice PREMIUM places order for 2 books:");
        System.out.println("  " + service.placeOrder(javaBookId, 2, premium));

        System.out.println("\nBob STANDARD places order for 1 book:");
        System.out.println("  " + service.placeOrder(javaBookId, 1, standard));

        System.out.println("\nCarol GUEST places order for 3 books:");
        System.out.println("  " + service.placeOrder(javaBookId, 3, guest));

        System.out.println("\n--- Recap: IoC vs DI vs DIP ---");
        System.out.println("  DIP  → design principle: depend on abstractions (interfaces)");
        System.out.println("  DI   → implementation: inject dependencies via constructor");
        System.out.println("  IoC  → architectural: the framework (Spring) controls wiring & lifecycle");
        System.out.println("  Here we did DI manually — Spring automates exactly this.");
    }
}
