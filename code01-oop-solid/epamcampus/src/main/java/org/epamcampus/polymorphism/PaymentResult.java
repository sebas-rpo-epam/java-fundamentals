package org.epamcampus.polymorphism;

public record PaymentResult(boolean success, String message, String transactionId) {

    public static PaymentResult success(String txId) {
        return new PaymentResult(true, "Payment approved", txId);
    }

    public static PaymentResult failure(String reason) {
        return new PaymentResult(false, reason, null);
    }

    @Override
    public String toString() {
        return success
                ? "SUCCESS [txId=%s] %s".formatted(transactionId, message)
                : "FAILED  %s".formatted(message);
    }
}
