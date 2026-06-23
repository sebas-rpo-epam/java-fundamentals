package org.epamcampus.dp.creational.abstractfactory.impl.production;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationChannel;
import org.epamcampus.dp.creational.abstractfactory.domain.Notification;

public final class PushChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("  [PUSH → " + notification.recipient() + "] " + notification.title());
    }

    @Override
    public String channelName() { return "PUSH"; }
}
