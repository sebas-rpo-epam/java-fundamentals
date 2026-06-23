package org.epamcampus.dp.behavioral.strategy.impl;

import org.epamcampus.dp.behavioral.strategy.pattern.ShippingStrategy;
import org.epamcampus.dp.behavioral.strategy.domain.Money;
import org.epamcampus.dp.behavioral.strategy.domain.Order;
import org.epamcampus.dp.behavioral.strategy.domain.ShippingType;

import java.math.BigDecimal;

public class StandardShipping implements ShippingStrategy {

    @Override
    public Money calculate(Order order) {
        // Base $5 + $2 per kg
        BigDecimal cost = new BigDecimal("5").add(new BigDecimal("2").multiply(order.weightKg()));
        return Money.usd(cost);
    }

    @Override
    public boolean supports(Order order, ShippingType type) {
        return type == ShippingType.STANDARD;
    }

    @Override
    public String providerName() { return "Standard Shipping"; }
}
