package org.epamcampus.dp.creational.abstractfactory.impl.sandbox;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationChannel;
import org.epamcampus.dp.creational.abstractfactory.domain.Notification;

public final class SandboxPushChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("  [SANDBOX-PUSH → " + notification.recipient() + "] "
                + notification.title() + " (not actually sent)");
    }

    @Override
    public String channelName() { return "SANDBOX-PUSH"; }
}
