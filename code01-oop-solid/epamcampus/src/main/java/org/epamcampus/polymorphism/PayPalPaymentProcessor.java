package org.epamcampus.polymorphism;

import java.util.UUID;

public class PayPalPaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentResult process(PaymentRequest request) {
        System.out.println("  [PayPal] Executing payment of $" + request.amount() + " for order " + request.orderId());
        return PaymentResult.success("paypal-" + UUID.randomUUID().toString().substring(0, 8));
    }

    @Override
    public boolean supports(PaymentMethod method) {
        return method == PaymentMethod.PAYPAL;
    }
}
