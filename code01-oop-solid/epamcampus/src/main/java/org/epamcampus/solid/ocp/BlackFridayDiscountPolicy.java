package org.epamcampus.solid.ocp;

import java.math.BigDecimal;
import java.time.LocalDate;

// Another new policy — zero changes to DiscountEngine or existing policies
public class BlackFridayDiscountPolicy implements DiscountPolicy {
    private static final BigDecimal RATE = new BigDecimal("0.30");

    @Override public boolean isApplicable(Order o, Customer c) {
        // Active only during Black Friday week
        LocalDate today = LocalDate.now();
        return today.getMonthValue() == 11 && today.getDayOfMonth() >= 25;
    }
    @Override public BigDecimal calculateDiscount(Order o) {
        return o.total().multiply(RATE);
    }
    @Override public String getPolicyName() { return "Black Friday 30%"; }
}
