package org.epamcampus.dp.behavioral.observer.impl;

import org.epamcampus.dp.behavioral.observer.pattern.DomainEventListener;
import org.epamcampus.dp.behavioral.observer.pattern.DomainEvent;
import org.epamcampus.dp.behavioral.observer.pattern.OrderPlacedEvent;

public class EmailNotificationListener implements DomainEventListener {

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof OrderPlacedEvent e) {
            System.out.println("  [EMAIL] Sending order confirmation to customer "
                    + e.customerId() + " for order " + e.orderId());
        }
    }

    @Override
    public boolean supports(Class<? extends DomainEvent> eventType) {
        return eventType == OrderPlacedEvent.class;
    }

    @Override
    public String listenerName() { return "EmailNotificationListener"; }
}
