package org.epamcampus.encapsulation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaction(
        TransactionType type,
        BigDecimal amount,
        LocalDateTime timestamp
) {
    @Override
    public String toString() {
        return "[%s] %s $%.2f".formatted(timestamp.toLocalTime(), type, amount);
    }
}
