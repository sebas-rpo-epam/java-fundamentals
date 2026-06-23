package org.epamcampus.dp.behavioral.observer.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderConfirmation(UUID orderId, String customerId, BigDecimal total) {}
