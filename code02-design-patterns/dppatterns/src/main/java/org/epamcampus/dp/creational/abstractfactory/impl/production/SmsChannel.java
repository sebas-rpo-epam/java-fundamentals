package org.epamcampus.dp.creational.abstractfactory.impl.production;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationChannel;
import org.epamcampus.dp.creational.abstractfactory.domain.Notification;

public final class SmsChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("  [SMS → " + notification.recipient() + "] " + notification.body());
    }

    @Override
    public String channelName() { return "SMS"; }
}
