package org.epamcampus.ioc;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Product product, Customer customer);
}
