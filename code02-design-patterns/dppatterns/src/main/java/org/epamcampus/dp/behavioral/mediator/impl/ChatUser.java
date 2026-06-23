package org.epamcampus.dp.behavioral.mediator.impl;

import org.epamcampus.dp.behavioral.mediator.pattern.ChatParticipant;
import org.epamcampus.dp.behavioral.mediator.pattern.Mediator;

import java.util.Objects;

public class ChatUser implements ChatParticipant {

    private final String name;
    private final Mediator mediator;

    public ChatUser(String name, Mediator mediator) {
        this.name     = Objects.requireNonNull(name, "name must not be null");
        this.mediator = Objects.requireNonNull(mediator, "mediator must not be null");
    }

    public void sendMessage(String message) {
        mediator.sendAll(name, message);
    }

    public void sendMessageTo(String target, String message) {
        mediator.sendTo(name, target, message);
    }

    @Override
    public void receive(String from, String message) {
        System.out.println("  [" + name + " received from " + from + "]: " + message);
    }

    @Override
    public String getName() { return name; }
}
