package org.epamcampus.dp.creational.abstractfactory.impl;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationFactory;
import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.production.EmailChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.production.SmsChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.production.PushChannel;

public class ProductionNotificationFactory implements NotificationFactory {
    @Override
    public NotificationChannel emailChannel() { return new EmailChannel(); }

    @Override
    public NotificationChannel smsChannel() { return new SmsChannel(); }

    @Override
    public NotificationChannel pushChannel() { return new PushChannel(); }

    @Override
    public String environmentName() { return "PRODUCTION"; }
}
