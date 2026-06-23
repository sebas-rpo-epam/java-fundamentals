package org.epamcampus.dp.behavioral.strategy.impl;

import org.epamcampus.dp.behavioral.strategy.pattern.ShippingStrategy;
import org.epamcampus.dp.behavioral.strategy.domain.Money;
import org.epamcampus.dp.behavioral.strategy.domain.Order;
import org.epamcampus.dp.behavioral.strategy.domain.ShippingType;

import java.math.BigDecimal;

public class OvernightShipping implements ShippingStrategy {

    @Override
    public Money calculate(Order order) {
        // Base $35 + $10 per kg
        BigDecimal cost = new BigDecimal("35").add(new BigDecimal("10").multiply(order.weightKg()));
        return Money.usd(cost);
    }

    @Override
    public boolean supports(Order order, ShippingType type) {
        return type == ShippingType.OVERNIGHT;
    }

    @Override
    public String providerName() { return "Overnight Shipping"; }
}
