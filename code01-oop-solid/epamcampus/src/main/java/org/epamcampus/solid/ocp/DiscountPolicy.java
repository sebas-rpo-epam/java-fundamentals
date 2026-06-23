package org.epamcampus.solid.ocp;

import java.math.BigDecimal;

// ✅ Open for extension: each new discount = a new class implementing this
// Closed for modification: DiscountEngine never changes
public interface DiscountPolicy {
    boolean isApplicable(Order order, Customer customer);
    BigDecimal calculateDiscount(Order order);
    String getPolicyName();
}
