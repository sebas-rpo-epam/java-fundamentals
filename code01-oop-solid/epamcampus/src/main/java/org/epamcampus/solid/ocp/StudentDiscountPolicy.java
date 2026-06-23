package org.epamcampus.solid.ocp;

import java.math.BigDecimal;

public class StudentDiscountPolicy implements DiscountPolicy {
    private static final BigDecimal RATE = new BigDecimal("0.10");

    @Override public boolean isApplicable(Order o, Customer c) {
        return c.type() == CustomerType.STUDENT;
    }
    @Override public BigDecimal calculateDiscount(Order o) {
        return o.total().multiply(RATE);
    }
    @Override public String getPolicyName() { return "Student 10%"; }
}
