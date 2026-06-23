package org.epamcampus.solid.srp;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record Order(UUID id, String customerEmail, List<Item> items) {

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(i -> i.price().multiply(new BigDecimal(i.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
