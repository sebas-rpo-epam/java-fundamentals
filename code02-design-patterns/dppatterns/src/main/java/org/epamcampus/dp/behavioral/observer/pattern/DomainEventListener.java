package org.epamcampus.dp.behavioral.observer.pattern;

public interface DomainEventListener {
    void onEvent(DomainEvent event);
    boolean supports(Class<? extends DomainEvent> eventType);
    String listenerName();
}
