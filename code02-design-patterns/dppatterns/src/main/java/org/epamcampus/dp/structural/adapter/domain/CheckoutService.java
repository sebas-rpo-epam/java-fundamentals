package org.epamcampus.dp.structural.adapter.domain;

import org.epamcampus.dp.structural.adapter.pattern.PaymentGateway;

import java.util.Objects;
import java.util.UUID;

public class CheckoutService {

    private final PaymentGateway gateway; // ✅ depends on abstraction, not implementation

    public CheckoutService(PaymentGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway, "gateway must not be null");
    }

    public OrderReceipt checkout(String customerId, Money amount) {
        System.out.println("  [Checkout] Charging " + amount + " via " + gateway.gatewayName());
        PaymentResult result = gateway.charge(customerId, amount);
        UUID orderId = UUID.randomUUID();
        if (result.success()) {
            System.out.println("  [Checkout] Payment OK — txn: " + result.transactionId());
        } else {
            System.out.println("  [Checkout] Payment FAILED — " + result.message());
        }
        return new OrderReceipt(orderId, customerId, amount, result);
    }
}
