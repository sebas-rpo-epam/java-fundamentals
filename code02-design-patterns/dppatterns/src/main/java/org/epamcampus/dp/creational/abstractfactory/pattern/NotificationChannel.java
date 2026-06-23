package org.epamcampus.dp.creational.abstractfactory.pattern;

import org.epamcampus.dp.creational.abstractfactory.domain.Notification;

// ─── Product interface for Abstract Factory ────────────────────────────────
// Not sealed: the factory itself controls which implementations are created —
// exhaustiveness is enforced structurally, not by the sealed keyword.
// In a named module you could use sealed + cross-package permits; in an
// unnamed module (standard Maven) all permitted subtypes must share the
// same package, which conflicts with the production/sandbox split here.
public interface NotificationChannel {
    void send(Notification notification);
    String channelName();
}
