package org.epamcampus.dp.creational.abstractfactory.bad;

import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.production.EmailChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.production.SmsChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.production.PushChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.sandbox.SandboxEmailChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.sandbox.SandboxSmsChannel;
import org.epamcampus.dp.creational.abstractfactory.impl.sandbox.SandboxPushChannel;
import org.epamcampus.dp.creational.abstractfactory.domain.Notification;

/**
 * ❌ PROBLEM: Hardcoded channel creation — cannot test without real email/SMS infra.
 * Adding a new env (staging) requires modifying this class.
 */
public class BadNotificationService {

    private final NotificationChannel emailChannel;
    private final NotificationChannel smsChannel;
    private final NotificationChannel pushChannel;

    public BadNotificationService(String env) {
        // ❌ Direct construction — if (env) hardcoded inside constructor
        if (env.equals("production")) {
            this.emailChannel = new EmailChannel();
            this.smsChannel = new SmsChannel();
            this.pushChannel = new PushChannel();
        } else {
            // ❌ Only two cases — "staging" doesn't exist yet without modifying this
            this.emailChannel = new SandboxEmailChannel();
            this.smsChannel = new SandboxSmsChannel();
            this.pushChannel = new SandboxPushChannel();
        }
    }

    public void notifyEmail(Notification n) { emailChannel.send(n); }
    public void notifySms(Notification n)   { smsChannel.send(n); }
    public void notifyPush(Notification n)  { pushChannel.send(n); }
}
