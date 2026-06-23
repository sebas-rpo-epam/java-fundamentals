package org.epamcampus.dp.creational.abstractfactory.demo;

import org.epamcampus.dp.creational.abstractfactory.bad.BadNotificationService;
import org.epamcampus.dp.creational.abstractfactory.domain.Notification;
import org.epamcampus.dp.creational.abstractfactory.domain.NotificationService;
import org.epamcampus.dp.creational.abstractfactory.pattern.NotificationFactory;
import org.epamcampus.dp.creational.abstractfactory.impl.ProductionNotificationFactory;
import org.epamcampus.dp.creational.abstractfactory.impl.SandboxNotificationFactory;

public class MainAbstractFactory {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║      PATTERN 2: ABSTRACT FACTORY         ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Notification orderNotif = new Notification(
                "Order Confirmed",
                "Your order #1234 has been placed.",
                "alice@epam.com"
        );

        System.out.println("\n--- BAD: Hardcoded env string in constructor ---");
        // BadNotificationService must be modified to add a 'staging' env
        BadNotificationService bad = new BadNotificationService("sandbox");
        bad.notifyEmail(orderNotif);
        System.out.println("  (Adding 'staging' env requires editing BadNotificationService source code)");

        System.out.println("\n--- GOOD: NotificationService with ProductionNotificationFactory ---");
        NotificationFactory prodFactory = new ProductionNotificationFactory();
        NotificationService prodService = new NotificationService(prodFactory);
        System.out.println("  Environment: " + prodFactory.environmentName());
        prodService.notifyEmail(orderNotif);
        prodService.notifySms(orderNotif);
        prodService.notifyPush(orderNotif);

        System.out.println("\n--- GOOD: Same NotificationService, swapped to SandboxNotificationFactory ---");
        NotificationFactory sandboxFactory = new SandboxNotificationFactory();
        NotificationService sandboxService = new NotificationService(sandboxFactory);
        System.out.println("  Environment: " + sandboxFactory.environmentName());
        sandboxService.notifyEmail(orderNotif);
        sandboxService.notifySms(orderNotif);
        sandboxService.notifyPush(orderNotif);

        System.out.println("\nKey insight: Swapping prod→sandbox requires zero changes to NotificationService." +
                " Inject a different factory — every channel switches atomically.");
    }
}
