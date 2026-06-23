package org.epamcampus.dp.structural.adapter.domain;

public record PaymentResult(boolean success, String transactionId, String message) {

    public static PaymentResult success(String txId) {
        return new PaymentResult(true, txId, "Payment successful");
    }

    public static PaymentResult failure(String reason) {
        return new PaymentResult(false, null, reason);
    }
}
