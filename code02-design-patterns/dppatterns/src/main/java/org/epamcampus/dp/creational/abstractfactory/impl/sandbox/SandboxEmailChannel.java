package org.epamcampus.dp.creational.abstractfactory.impl.sandbox;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationChannel;
import org.epamcampus.dp.creational.abstractfactory.domain.Notification;

public final class SandboxEmailChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("  [SANDBOX-EMAIL → " + notification.recipient() + "] "
                + notification.title() + ": " + notification.body() + " (not actually sent)");
    }

    @Override
    public String channelName() { return "SANDBOX-EMAIL"; }
}
