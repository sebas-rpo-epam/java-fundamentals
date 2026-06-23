package org.epamcampus.dp.structural.decorator.demo;

import org.epamcampus.dp.structural.decorator.bad.BadMonolithicRepository;
import org.epamcampus.dp.structural.decorator.domain.Order;
import org.epamcampus.dp.structural.decorator.impl.InMemoryOrderRepository;
import org.epamcampus.dp.structural.decorator.impl.LoggingOrderRepository;
import org.epamcampus.dp.structural.decorator.impl.CachingOrderRepository;
import org.epamcampus.dp.structural.decorator.impl.MetricsOrderRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class MainDecorator {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         PATTERN 5: DECORATOR             ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n--- BAD: BadMonolithicRepository (all concerns in one class) ---");
        BadMonolithicRepository bad = new BadMonolithicRepository();
        UUID id1 = UUID.randomUUID();
        bad.save(new Order(id1, "customer-001", new BigDecimal("99.99"), "PLACED"));
        bad.findById(id1);

        System.out.println("\n--- GOOD: Decorated stack: Metrics → Caching → Logging → InMemory ---");
        // Spring equivalent: @Cacheable + @Transactional + Actuator metrics — all decorators under the hood
        MetricsOrderRepository repo = new MetricsOrderRepository(
                new CachingOrderRepository(
                        new LoggingOrderRepository(
                                new InMemoryOrderRepository()
                        )
                )
        );

        UUID orderId1 = UUID.randomUUID();
        UUID orderId2 = UUID.randomUUID();

        System.out.println("\n  [Action] Saving order 1...");
        repo.save(new Order(orderId1, "customer-001", new BigDecimal("149.99"), "PLACED"));

        System.out.println("\n  [Action] Saving order 2...");
        repo.save(new Order(orderId2, "customer-002", new BigDecimal("59.99"), "PLACED"));

        System.out.println("\n  [Action] findById (first call = CACHE MISS)...");
        repo.findById(orderId1);

        System.out.println("\n  [Action] findById (second call = CACHE HIT)...");
        repo.findById(orderId1);

        System.out.println("\n  [Action] findAll...");
        var all = repo.findAll();
        System.out.println("  Found " + all.size() + " orders");

        System.out.println();
        repo.printSummary();

        System.out.println("\nKey insight: Each concern (logging, caching, metrics) lives in its own class." +
                " Stack them in any order. Remove one layer without touching the others.");
        // Spring equivalent: @Cacheable + @Transactional + Actuator metrics — all decorators under the hood
    }
}
