package org.epamcampus.dp.behavioral.mediator.pattern;

public interface ChatParticipant {
    void receive(String from, String message);
    String getName();
}
