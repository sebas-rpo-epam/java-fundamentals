package org.epamcampus.dp.behavioral.strategy.impl;

import org.epamcampus.dp.behavioral.strategy.pattern.ShippingStrategy;
import org.epamcampus.dp.behavioral.strategy.domain.Money;
import org.epamcampus.dp.behavioral.strategy.domain.Order;
import org.epamcampus.dp.behavioral.strategy.domain.ShippingType;

import java.math.BigDecimal;

public class InternationalShipping implements ShippingStrategy {

    @Override
    public Money calculate(Order order) {
        // Base $25 + $8 per kg, min $40
        BigDecimal cost = new BigDecimal("25").add(new BigDecimal("8").multiply(order.weightKg()));
        BigDecimal min  = new BigDecimal("40");
        return Money.usd(cost.max(min));
    }

    @Override
    public boolean supports(Order order, ShippingType type) {
        return type == ShippingType.INTERNATIONAL;
    }

    @Override
    public String providerName() { return "International Shipping"; }
}
