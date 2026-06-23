package org.epamcampus.dp.structural.composite.demo;

import org.epamcampus.dp.structural.composite.bad.BadAuthorizationService;
import org.epamcampus.dp.structural.composite.domain.User;
import org.epamcampus.dp.structural.composite.domain.AuthorizationService;
import org.epamcampus.dp.structural.composite.domain.PermissionRegistry;
import org.epamcampus.dp.structural.composite.pattern.Permission;
import org.epamcampus.dp.structural.composite.impl.SimplePermission;

import java.util.Set;

public class MainComposite {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         PATTERN 4: COMPOSITE             ║");
        System.out.println("╚══════════════════════════════════════════╝");

        User alice = new User("u1", "Alice",
                Set.of("ADMIN"),
                Set.of("READ", "WRITE", "DELETE"));

        User bob = new User("u2", "Bob",
                Set.of("PREMIUM"),
                Set.of("READ"));

        User carol = new User("u3", "Carol",
                Set.of("MANAGER"),
                Set.of("HR_WRITE"));

        User dave = new User("u4", "Dave",
                Set.of(),
                Set.of());

        System.out.println("\n--- BAD: BadAuthorizationService — hardcoded if-else ---");
        BadAuthorizationService bad = new BadAuthorizationService();
        System.out.println("  Alice canAccessHrModule: " + bad.canAccessHrModule(alice));
        System.out.println("  Dave  canAccessHrModule: " + bad.canAccessHrModule(dave));
        System.out.println("  (Adding new permission = modifying BadAuthorizationService source)");

        System.out.println("\n--- GOOD: Composite Permission tree with AuthorizationService ---");
        AuthorizationService authz = new AuthorizationService();

        Permission adminRole   = PermissionRegistry.adminRole();
        Permission viewerRole  = PermissionRegistry.viewerRole();
        Permission managerHr   = PermissionRegistry.managerHrAccess();

        // Leaf permission — simple check
        Permission canRead = new SimplePermission("CAN_READ", u -> u.directPermissions().contains("READ"));

        System.out.println("\n  -- Leaf permission --");
        authz.check(alice, canRead);
        authz.check(dave,  canRead);

        System.out.println("\n  -- Composite: ADMIN_ROLE (ALL OF: READ + WRITE + DELETE) --");
        authz.check(alice, adminRole);   // all three permissions → GRANTED
        authz.check(bob,   adminRole);   // missing WRITE, DELETE → DENIED

        System.out.println("\n  -- Composite: VIEWER_ROLE (ANY OF: PREMIUM or CAN_READ) --");
        authz.check(bob,   viewerRole);  // IS_PREMIUM → GRANTED
        authz.check(carol, viewerRole);  // CAN_READ → GRANTED (carol has READ via HR_WRITE? No — DENIED)
        authz.check(dave,  viewerRole);  // neither → DENIED

        System.out.println("\n  -- Composite: MANAGER_HR_ACCESS (ALL OF) --");
        authz.check(carol, managerHr);   // IS_MANAGER + HAS_HR_WRITE → GRANTED
        authz.check(alice, managerHr);   // IS_MANAGER? No → DENIED

        System.out.println("\nKey insight: AuthorizationService treats leaf and composite permissions identically." +
                " New rules are new Permission objects — zero changes to AuthorizationService.");
    }
}
