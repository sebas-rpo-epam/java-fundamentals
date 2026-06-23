package org.epamcampus.dp.behavioral.observer.pattern;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {

    private final List<DomainEventListener> listeners = new ArrayList<>();

    public void subscribe(DomainEventListener listener) {
        listeners.add(listener);
        System.out.println("  [EventBus] Registered: " + listener.listenerName());
    }

    public void publish(DomainEvent event) {
        System.out.println("  [EventBus] Publishing: " + event.eventType());
        listeners.stream()
                .filter(l -> l.supports(event.getClass()))
                .forEach(l -> l.onEvent(event));
    }
}
