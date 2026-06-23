package org.epamcampus.solid.ocp;

import java.math.BigDecimal;

// New policy added WITHOUT touching any existing code — that's OCP in action
public class LoyaltyDiscountPolicy implements DiscountPolicy {
    private static final int MIN_PURCHASES = 10;
    private static final BigDecimal RATE   = new BigDecimal("0.12");

    @Override public boolean isApplicable(Order o, Customer c) {
        return c.totalPurchases() >= MIN_PURCHASES;
    }
    @Override public BigDecimal calculateDiscount(Order o) {
        return o.total().multiply(RATE);
    }
    @Override public String getPolicyName() { return "Loyalty 12% (10+ purchases)"; }
}
