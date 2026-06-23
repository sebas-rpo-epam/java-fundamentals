package org.epamcampus.polymorphism;

import java.util.UUID;

public class StripePaymentProcessor implements PaymentProcessor {

    @Override
    public PaymentResult process(PaymentRequest request) {
        System.out.println("  [Stripe] Charging $" + request.amount() + " via " + request.paymentMethod());
        return PaymentResult.success("stripe-" + UUID.randomUUID().toString().substring(0, 8));
    }

    @Override
    public boolean supports(PaymentMethod method) {
        return method == PaymentMethod.CREDIT_CARD || method == PaymentMethod.DEBIT_CARD;
    }
}
