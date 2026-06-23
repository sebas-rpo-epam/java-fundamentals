package org.epamcampus.dp.structural.adapter.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record Money(BigDecimal amount, String currency) {

    public Money {
        Objects.requireNonNull(amount, "amount must not be null");
        Objects.requireNonNull(currency, "currency must not be null");
    }

    public static Money of(BigDecimal amount, String currency) {
        return new Money(amount, currency);
    }

    @Override
    public String toString() {
        return currency + " " + amount;
    }
}
