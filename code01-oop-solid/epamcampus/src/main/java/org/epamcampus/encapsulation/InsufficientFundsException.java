package org.epamcampus.encapsulation;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String accountId, BigDecimal requested, BigDecimal available) {
        super("Account %s: cannot withdraw $%.2f, available balance is $%.2f"
                .formatted(accountId, requested, available));
    }
}
