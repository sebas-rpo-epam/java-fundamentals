package org.epamcampus.solid.ocp;

import java.math.BigDecimal;

/**
 * ❌ VIOLATES OCP — Every new discount type requires MODIFYING this class.
 * If you add "LOYALTY" or "BLACK_FRIDAY", you touch code that already works,
 * risking regression in STUDENT, SENIOR, EMPLOYEE logic.
 */
public class BadDiscountCalculator {

    public BigDecimal calculate(Order order, String discountType) {
        return switch (discountType) {
            case "STUDENT"  -> order.total().multiply(new BigDecimal("0.10"));
            case "SENIOR"   -> order.total().multiply(new BigDecimal("0.15"));
            case "EMPLOYEE" -> order.total().multiply(new BigDecimal("0.20"));
            // ← Adding "LOYALTY"     requires MODIFYING this class
            // ← Adding "BLACK_FRIDAY" requires MODIFYING this class
            default -> BigDecimal.ZERO;
        };
    }
}
