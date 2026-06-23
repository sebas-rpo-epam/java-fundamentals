package org.epamcampus.dp.behavioral.mediator.impl;

import org.epamcampus.dp.behavioral.mediator.pattern.Mediator;
import org.epamcampus.dp.behavioral.mediator.pattern.ChatParticipant;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChatRoom implements Mediator {

    private final Map<String, ChatParticipant> participants = new LinkedHashMap<>();

    @Override
    public void register(ChatParticipant participant) {
        participants.put(participant.getName(), participant);
        System.out.println("  [ChatRoom] " + participant.getName() + " joined");
    }

    @Override
    public void sendAll(String from, String message) {
        participants.values().stream()
                .filter(p -> !p.getName().equals(from))
                .forEach(p -> p.receive(from, message));
    }

    @Override
    public void sendTo(String from, String targetName, String message) {
        ChatParticipant target = participants.get(targetName);
        if (target != null) {
            target.receive(from, message);
        } else {
            System.out.println("  [ChatRoom] Target '" + targetName + "' not found");
        }
    }
}
