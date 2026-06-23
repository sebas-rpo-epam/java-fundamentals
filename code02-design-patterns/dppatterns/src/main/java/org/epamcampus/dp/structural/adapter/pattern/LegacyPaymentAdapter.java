package org.epamcampus.dp.structural.adapter.pattern;

import org.epamcampus.dp.structural.adapter.adaptee.LegacyPaymentSdk;
import org.epamcampus.dp.structural.adapter.domain.PaymentResult;
import org.epamcampus.dp.structural.adapter.domain.Money;

import java.math.BigDecimal;
import java.util.Objects;

public class LegacyPaymentAdapter implements PaymentGateway {

    private final LegacyPaymentSdk sdk; // ✅ wraps the incompatible SDK

    public LegacyPaymentAdapter(LegacyPaymentSdk sdk) {
        this.sdk = Objects.requireNonNull(sdk, "sdk must not be null");
    }

    @Override
    public PaymentResult charge(String customerId, Money amount) {
        try {
            // ✅ Adapter translates: Money → double cents, extracts currency, reorders args
            double amountInCents = amount.amount().multiply(new BigDecimal("100")).doubleValue();
            String currency = amount.currency();
            String txnId = sdk.processPayment(amountInCents, currency, customerId);
            return PaymentResult.success(txnId);
        } catch (Exception e) {
            return PaymentResult.failure(e.getMessage());
        }
    }

    @Override
    public String gatewayName() { return "LegacySDK (via Adapter)"; }
}
