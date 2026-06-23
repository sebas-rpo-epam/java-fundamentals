package org.epamcampus.dp.structural.adapter.domain;

import java.util.UUID;

public record OrderReceipt(UUID orderId, String customerId, Money amount, PaymentResult payment) {}
