package org.epamcampus.solid.srp;

public interface NotificationService {
    void sendOrderConfirmation(Order order);
    void sendShippingUpdate(Order order, String trackingCode);
}
