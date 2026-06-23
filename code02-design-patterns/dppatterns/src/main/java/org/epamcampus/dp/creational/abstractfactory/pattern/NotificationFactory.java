package org.epamcampus.dp.creational.abstractfactory.pattern;

public interface NotificationFactory {
    NotificationChannel emailChannel();
    NotificationChannel smsChannel();
    NotificationChannel pushChannel();
    String environmentName();
}
