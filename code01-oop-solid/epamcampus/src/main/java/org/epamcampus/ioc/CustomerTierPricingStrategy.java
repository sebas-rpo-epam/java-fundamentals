package org.epamcampus.ioc;

import java.math.BigDecimal;

// @Component  ← in Spring this annotation registers it in the IoC container
public class CustomerTierPricingStrategy implements PricingStrategy {

    @Override
    public BigDecimal calculatePrice(Product product, Customer customer) {
        BigDecimal multiplier = switch (customer.tier()) {
            case PREMIUM  -> new BigDecimal("0.85");  // 15% off
            case STANDARD -> BigDecimal.ONE;
            case GUEST    -> new BigDecimal("1.05");  // 5% surcharge
        };
        System.out.println("  Pricing [" + customer.tier() + "]: base $"
                + product.basePrice() + " × " + multiplier);
        return product.basePrice().multiply(multiplier);
    }
}
