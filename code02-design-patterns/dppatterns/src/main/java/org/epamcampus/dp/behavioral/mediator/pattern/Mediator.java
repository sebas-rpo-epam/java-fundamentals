package org.epamcampus.dp.behavioral.mediator.pattern;

public interface Mediator {
    void register(ChatParticipant participant);
    void sendAll(String from, String message);
    void sendTo(String from, String targetName, String message);
}
