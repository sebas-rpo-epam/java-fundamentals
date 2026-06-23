package org.epamcampus.polymorphism;

import java.util.List;

public class PaymentService {

    // Dynamic polymorphism: this list holds objects of different concrete types
    private final List<PaymentProcessor> processors;

    public PaymentService(List<PaymentProcessor> processors) {
        this.processors = List.copyOf(processors);
    }

    public PaymentResult pay(PaymentRequest request) {
        return processors.stream()
                .filter(p -> p.supports(request.paymentMethod()))   // dynamic dispatch
                .findFirst()
                .map(p -> p.process(request))                       // dynamic dispatch
                .orElse(PaymentResult.failure("Unsupported payment method: " + request.paymentMethod()));
    }
}
