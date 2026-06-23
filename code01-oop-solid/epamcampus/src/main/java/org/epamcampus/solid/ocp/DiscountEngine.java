package org.epamcampus.solid.ocp;

import java.math.BigDecimal;
import java.util.List;

// ✅ CLOSED for modification — this class NEVER changes when new discounts are added
public class DiscountEngine {

    private final List<DiscountPolicy> policies;

    public DiscountEngine(List<DiscountPolicy> policies) {
        this.policies = List.copyOf(policies);
    }

    public BigDecimal calculateBestDiscount(Order order, Customer customer) {
        return policies.stream()
                .filter(p -> p.isApplicable(order, customer))
                .peek(p -> System.out.println("  Applicable policy: " + p.getPolicyName()
                        + " → -$" + p.calculateDiscount(order)))
                .map(p -> p.calculateDiscount(order))
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }
}
