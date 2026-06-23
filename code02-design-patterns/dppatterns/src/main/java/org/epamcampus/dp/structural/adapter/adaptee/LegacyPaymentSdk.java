package org.epamcampus.dp.structural.adapter.adaptee;

/**
 * Third-party SDK — cannot be modified.
 * Incompatible API: wrong parameter order, uses double+cents, returns raw String.
 */
public class LegacyPaymentSdk {

    public String processPayment(double amountInCents, String currencyCode, String userId) {
        if (amountInCents < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        // Simulate legacy processing
        return "TXN-" + System.nanoTime();
    }
}
