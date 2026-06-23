package org.epamcampus.dp.structural.adapter.impl;

import org.epamcampus.dp.structural.adapter.pattern.PaymentGateway;
import org.epamcampus.dp.structural.adapter.domain.PaymentResult;
import org.epamcampus.dp.structural.adapter.domain.Money;

import java.util.UUID;

public class StripeGateway implements PaymentGateway {

    @Override
    public PaymentResult charge(String customerId, Money amount) {
        // ✅ Modern gateway — native implementation, no adapter needed
        String txnId = "stripe_" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return PaymentResult.success(txnId);
    }

    @Override
    public String gatewayName() { return "Stripe"; }
}
