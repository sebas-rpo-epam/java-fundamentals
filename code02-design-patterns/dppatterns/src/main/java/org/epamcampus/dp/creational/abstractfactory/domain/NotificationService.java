package org.epamcampus.dp.creational.abstractfactory.domain;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationFactory;

import java.util.Objects;

public class NotificationService {

    private final NotificationFactory factory; // ✅ depends only on the abstraction

    public NotificationService(NotificationFactory factory) {
        this.factory = Objects.requireNonNull(factory, "factory must not be null");
    }

    public void notifyEmail(Notification notification) {
        factory.emailChannel().send(notification);
    }

    public void notifySms(Notification notification) {
        factory.smsChannel().send(notification);
    }

    public void notifyPush(Notification notification) {
        factory.pushChannel().send(notification);
    }
}
