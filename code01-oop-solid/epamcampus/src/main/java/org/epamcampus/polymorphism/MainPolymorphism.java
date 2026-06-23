package org.epamcampus.polymorphism;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MainPolymorphism {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         POLYMORPHISM DEMO                ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ─── STATIC POLYMORPHISM: Overloading (resolved at compile time) ──
        System.out.println("\n--- Static Polymorphism: Overloading (early binding) ---");
        NotificationService notifier = new NotificationService();

        // The compiler decides which send() based on the argument type — NOT the object's type
        notifier.send("Your order has been placed!");
        notifier.send(new NotificationService.Email("alice@epam.com", "Welcome!", "Hello Alice"));
        notifier.send(new NotificationService.PushNotification("device-abc123", "New message!"));

        System.out.println("\nKey insight: Java chose the correct send() at COMPILE TIME");
        System.out.println("  → String arg  → send(String)");
        System.out.println("  → Email arg   → send(Email)");
        System.out.println("  → Push arg    → send(PushNotification)");

        // ─── DYNAMIC POLYMORPHISM: Overriding (resolved at runtime) ───────
        System.out.println("\n--- Dynamic Polymorphism: Overriding (late binding) ---");

        // PaymentService doesn't know or care about concrete types at compile time
        PaymentService paymentService = new PaymentService(List.of(
                new StripePaymentProcessor(),
                new PayPalPaymentProcessor()
        ));

        UUID orderId = UUID.randomUUID();

        PaymentRequest creditCardPayment = new PaymentRequest(orderId, new BigDecimal("99.99"), "USD", PaymentMethod.CREDIT_CARD);
        PaymentRequest payPalPayment     = new PaymentRequest(orderId, new BigDecimal("49.50"), "USD", PaymentMethod.PAYPAL);
        PaymentRequest cryptoPayment     = new PaymentRequest(orderId, new BigDecimal("200.00"), "USD", PaymentMethod.CRYPTO);

        System.out.println("\nProcessing CREDIT_CARD payment:");
        System.out.println("  Result: " + paymentService.pay(creditCardPayment));

        System.out.println("\nProcessing PAYPAL payment:");
        System.out.println("  Result: " + paymentService.pay(payPalPayment));

        System.out.println("\nProcessing CRYPTO payment (no processor registered):");
        System.out.println("  Result: " + paymentService.pay(cryptoPayment));

        System.out.println("\nKey insight: PaymentService.pay() NEVER changed.");
        System.out.println("  Adding a CryptoPaymentProcessor requires ZERO changes to PaymentService.");
        System.out.println("  → This is the Open/Closed Principle enabled by dynamic polymorphism.");
    }
}
