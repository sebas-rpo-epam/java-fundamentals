package org.epamcampus.dp.behavioral.strategy.pattern;

import org.epamcampus.dp.behavioral.strategy.domain.Money;
import org.epamcampus.dp.behavioral.strategy.domain.Order;
import org.epamcampus.dp.behavioral.strategy.domain.ShippingType;

public interface ShippingStrategy {
    Money calculate(Order order);
    boolean supports(Order order, ShippingType type);
    String providerName();
}
