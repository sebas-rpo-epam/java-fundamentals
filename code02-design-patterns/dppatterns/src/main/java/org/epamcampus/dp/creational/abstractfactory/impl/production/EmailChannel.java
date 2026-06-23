package org.epamcampus.dp.creational.abstractfactory.impl.production;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationChannel;
import org.epamcampus.dp.creational.abstractfactory.domain.Notification;

public final class EmailChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("  [EMAIL → " + notification.recipient() + "] "
                + notification.title() + ": " + notification.body());
    }

    @Override
    public String channelName() { return "EMAIL"; }
}
