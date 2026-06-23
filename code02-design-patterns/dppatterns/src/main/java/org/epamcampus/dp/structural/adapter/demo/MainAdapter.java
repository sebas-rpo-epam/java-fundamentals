package org.epamcampus.dp.structural.adapter.demo;

import org.epamcampus.dp.structural.adapter.domain.Money;
import org.epamcampus.dp.structural.adapter.domain.CheckoutService;
import org.epamcampus.dp.structural.adapter.domain.OrderReceipt;
import org.epamcampus.dp.structural.adapter.pattern.LegacyPaymentAdapter;
import org.epamcampus.dp.structural.adapter.adaptee.LegacyPaymentSdk;
import org.epamcampus.dp.structural.adapter.impl.StripeGateway;

import java.math.BigDecimal;

public class MainAdapter {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          PATTERN 3: ADAPTER              ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Money price = Money.of(new BigDecimal("49.99"), "USD");

        System.out.println("\n--- GOOD: CheckoutService with Stripe (modern, native) ---");
        CheckoutService stripeCheckout = new CheckoutService(new StripeGateway());
        OrderReceipt stripeReceipt = stripeCheckout.checkout("customer-001", price);
        System.out.println("  Receipt: order=" + stripeReceipt.orderId()
                + " | success=" + stripeReceipt.payment().success());

        System.out.println("\n--- GOOD: CheckoutService with LegacyPaymentAdapter ---");
        // ✅ Same CheckoutService, different gateway — adapter hides the incompatible SDK
        CheckoutService legacyCheckout = new CheckoutService(
                new LegacyPaymentAdapter(new LegacyPaymentSdk())
        );
        OrderReceipt legacyReceipt = legacyCheckout.checkout("customer-001", price);
        System.out.println("  Receipt: order=" + legacyReceipt.orderId()
                + " | txn=" + legacyReceipt.payment().transactionId());

        System.out.println("\nKey insight: CheckoutService never changed." +
                " Adapter bridges the incompatible interface of LegacyPaymentSdk" +
                " without modifying either the service or the SDK.");
    }
}
