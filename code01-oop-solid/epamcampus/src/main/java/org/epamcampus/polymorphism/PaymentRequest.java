package org.epamcampus.polymorphism;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
        UUID orderId,
        BigDecimal amount,
        String currency,
        PaymentMethod paymentMethod
) {}
