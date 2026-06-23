package org.epamcampus.solid.ocp;

import java.math.BigDecimal;

public class SeniorDiscountPolicy implements DiscountPolicy {
    private static final BigDecimal RATE = new BigDecimal("0.15");

    @Override public boolean isApplicable(Order o, Customer c) {
        return c.type() == CustomerType.SENIOR;
    }
    @Override public BigDecimal calculateDiscount(Order o) {
        return o.total().multiply(RATE);
    }
    @Override public String getPolicyName() { return "Senior 15%"; }
}
