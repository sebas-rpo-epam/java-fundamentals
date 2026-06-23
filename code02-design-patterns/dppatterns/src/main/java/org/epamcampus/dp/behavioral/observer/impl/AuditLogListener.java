package org.epamcampus.dp.behavioral.observer.impl;

import org.epamcampus.dp.behavioral.observer.pattern.DomainEventListener;
import org.epamcampus.dp.behavioral.observer.pattern.DomainEvent;

public class AuditLogListener implements DomainEventListener {

    @Override
    public void onEvent(DomainEvent event) {
        System.out.println("  [AUDIT] Event: " + event.eventType() + " at " + event.occurredAt());
    }

    @Override
    public boolean supports(Class<? extends DomainEvent> eventType) {
        return true; // ✅ handles all events
    }

    @Override
    public String listenerName() { return "AuditLogListener"; }
}
