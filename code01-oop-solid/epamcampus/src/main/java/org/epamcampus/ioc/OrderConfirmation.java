package org.epamcampus.ioc;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderConfirmation(UUID orderId, Product product, int quantity, BigDecimal total) {
    @Override
    public String toString() {
        return "Order %s: %dx %s @ $%s each = $%s"
                .formatted(orderId.toString().substring(0, 8),
                        quantity, product.name(),
                        total.divide(new BigDecimal(quantity), 2, java.math.RoundingMode.HALF_UP),
                        total);
    }
}
