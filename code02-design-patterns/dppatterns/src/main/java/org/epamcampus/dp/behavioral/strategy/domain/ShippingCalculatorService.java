package org.epamcampus.dp.behavioral.strategy.domain;

import org.epamcampus.dp.behavioral.strategy.pattern.ShippingStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ShippingCalculatorService {

    // In Spring Boot: @Autowired List<ShippingStrategy> strategies
    // — Spring injects ALL @Component beans that implement ShippingStrategy
    private final List<ShippingStrategy> strategies;

    public ShippingCalculatorService(List<ShippingStrategy> strategies) {
        this.strategies = List.copyOf(Objects.requireNonNull(strategies, "strategies must not be null"));
    }

    public Money calculateCheapest(Order order, ShippingType type) {
        return strategies.stream()
                .filter(s -> s.supports(order, type))
                .peek(s -> System.out.println("  [Strategy] " + s.providerName() + ": " + s.calculate(order)))
                .map(s -> s.calculate(order))
                .min(Comparator.comparing(Money::amount))
                .orElseThrow(() -> new IllegalArgumentException("No strategy found for type: " + type));
    }
}
