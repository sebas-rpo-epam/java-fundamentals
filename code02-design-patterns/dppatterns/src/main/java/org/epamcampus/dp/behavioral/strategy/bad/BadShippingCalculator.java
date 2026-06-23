package org.epamcampus.dp.behavioral.strategy.bad;

import org.epamcampus.dp.behavioral.strategy.domain.Order;
import org.epamcampus.dp.behavioral.strategy.domain.ShippingType;

import java.math.BigDecimal;

/**
 * ❌ PROBLEM: Switch statement — adding a new shipping carrier requires modifying this class.
 * Cannot add promotional free shipping without touching business logic.
 */
public class BadShippingCalculator {

    public BigDecimal calculate(Order order, ShippingType type) {
        // ❌ Every new carrier or promotion means a new case here
        return switch (type) {
            case STANDARD     -> new BigDecimal("5").add(new BigDecimal("2").multiply(order.weightKg()));
            case EXPRESS      -> new BigDecimal("15").add(new BigDecimal("5").multiply(order.weightKg()));
            case OVERNIGHT    -> new BigDecimal("35").add(new BigDecimal("10").multiply(order.weightKg()));
            case INTERNATIONAL -> new BigDecimal("25").add(new BigDecimal("8").multiply(order.weightKg())).max(new BigDecimal("40"));
            // ❌ Adding FreeShipping requires modifying this switch — OCP violation
        };
    }
}
