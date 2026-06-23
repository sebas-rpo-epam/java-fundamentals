package org.epamcampus.dp.behavioral.strategy.demo;

import org.epamcampus.dp.behavioral.strategy.bad.BadShippingCalculator;
import org.epamcampus.dp.behavioral.strategy.domain.Money;
import org.epamcampus.dp.behavioral.strategy.domain.Order;
import org.epamcampus.dp.behavioral.strategy.domain.ShippingType;
import org.epamcampus.dp.behavioral.strategy.domain.ShippingCalculatorService;
import org.epamcampus.dp.behavioral.strategy.impl.StandardShipping;
import org.epamcampus.dp.behavioral.strategy.impl.ExpressShipping;
import org.epamcampus.dp.behavioral.strategy.impl.OvernightShipping;
import org.epamcampus.dp.behavioral.strategy.impl.InternationalShipping;
import org.epamcampus.dp.behavioral.strategy.impl.FreeShippingStrategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MainStrategy {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         PATTERN 7: STRATEGY              ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n--- BAD: Switch statement — closed to extension ---");
        BadShippingCalculator bad = new BadShippingCalculator();
        Order badOrder = new Order(UUID.randomUUID(), new BigDecimal("2"), "US", new BigDecimal("30"));
        System.out.println("  STANDARD cost: $" + bad.calculate(badOrder, ShippingType.STANDARD));
        System.out.println("  (Adding free shipping requires modifying BadShippingCalculator)");

        System.out.println("\n--- GOOD: ShippingCalculatorService with all strategies ---");
        ShippingCalculatorService service = new ShippingCalculatorService(List.of(
                new StandardShipping(),
                new ExpressShipping(),
                new OvernightShipping(),
                new InternationalShipping(),
                new FreeShippingStrategy()
        ));

        Order smallOrder = new Order(UUID.randomUUID(), new BigDecimal("2"), "US", new BigDecimal("30.00"));

        System.out.println("\n  -- $30 order, 2kg (no free shipping) --");
        System.out.println("  STANDARD:");
        Money standardCost = service.calculateCheapest(smallOrder, ShippingType.STANDARD);
        System.out.println("  → Cheapest: " + standardCost);

        System.out.println("\n  EXPRESS:");
        Money expressCost = service.calculateCheapest(smallOrder, ShippingType.EXPRESS);
        System.out.println("  → Cheapest: " + expressCost);

        System.out.println("\n  OVERNIGHT:");
        Money overnightCost = service.calculateCheapest(smallOrder, ShippingType.OVERNIGHT);
        System.out.println("  → Cheapest: " + overnightCost);

        System.out.println("\n  INTERNATIONAL:");
        Money intlCost = service.calculateCheapest(smallOrder, ShippingType.INTERNATIONAL);
        System.out.println("  → Cheapest: " + intlCost);

        System.out.println("\n  -- $200 order, 2kg (FreeShipping applies) --");
        Order bigOrder = new Order(UUID.randomUUID(), new BigDecimal("2"), "US", new BigDecimal("200.00"));
        System.out.println("  STANDARD:");
        Money freeCost = service.calculateCheapest(bigOrder, ShippingType.STANDARD);
        System.out.println("  → Cheapest: " + freeCost + " (FreeShipping won!)");

        System.out.println("\nKey insight: Adding FreeShippingStrategy required zero changes to ShippingCalculatorService." +
                " In Spring Boot, just annotate FreeShippingStrategy with @Component — it gets injected automatically.");
    }
}
