package org.epamcampus.solid.srp;

public class ConsoleNotificationService implements NotificationService {

    @Override
    public void sendOrderConfirmation(Order order) {
        System.out.println("  [NotificationService] Email → " + order.customerEmail()
                + " | Order confirmed: $" + order.calculateTotal());
    }

    @Override
    public void sendShippingUpdate(Order order, String trackingCode) {
        System.out.println("  [NotificationService] Email → " + order.customerEmail()
                + " | Tracking: " + trackingCode);
    }
}
