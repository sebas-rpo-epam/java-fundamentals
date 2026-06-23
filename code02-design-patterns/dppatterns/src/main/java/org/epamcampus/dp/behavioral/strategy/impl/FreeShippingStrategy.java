package org.epamcampus.dp.behavioral.strategy.impl;

import org.epamcampus.dp.behavioral.strategy.pattern.ShippingStrategy;
import org.epamcampus.dp.behavioral.strategy.domain.Money;
import org.epamcampus.dp.behavioral.strategy.domain.Order;
import org.epamcampus.dp.behavioral.strategy.domain.ShippingType;

import java.math.BigDecimal;

public class FreeShippingStrategy implements ShippingStrategy {

    private static final BigDecimal THRESHOLD = new BigDecimal("150");

    @Override
    public Money calculate(Order order) {
        return Money.usd(BigDecimal.ZERO);
    }

    @Override
    public boolean supports(Order order, ShippingType type) {
        // ✅ Self-filtering: only applicable for STANDARD orders above the threshold
        return type == ShippingType.STANDARD
                && order.subtotal().compareTo(THRESHOLD) >= 0;
    }

    @Override
    public String providerName() { return "Free Shipping (orders $150+)"; }
}
