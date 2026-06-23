package org.epamcampus.dp.creational.abstractfactory.impl;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationFactory;
import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.sandbox.SandboxEmailChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.sandbox.SandboxSmsChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.sandbox.SandboxPushChannel;

public class SandboxNotificationFactory implements NotificationFactory {
    @Override
    public NotificationChannel emailChannel() { return new SandboxEmailChannel(); }

    @Override
    public NotificationChannel smsChannel() { return new SandboxSmsChannel(); }

    @Override
    public NotificationChannel pushChannel() { return new SandboxPushChannel(); }

    @Override
    public String environmentName() { return "SANDBOX"; }
}
