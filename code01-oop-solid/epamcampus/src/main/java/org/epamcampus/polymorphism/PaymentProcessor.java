package org.epamcampus.polymorphism;

public interface PaymentProcessor {
    PaymentResult process(PaymentRequest request);
    boolean supports(PaymentMethod method);
}
