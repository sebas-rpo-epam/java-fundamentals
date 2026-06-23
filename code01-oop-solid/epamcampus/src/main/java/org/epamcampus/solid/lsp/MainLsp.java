package org.epamcampus.solid.lsp;

import java.util.List;

public class MainLsp {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       SOLID — L: Liskov Substitution     ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ─── BAD: Penguin breaks the fly() contract at runtime ─────────────
        System.out.println("\n--- BAD: Subtype cannot substitute supertype ---");
        List<BadBird> birds = List.of(
                new BadBird("Sparrow"),
                new BadBird("Eagle"),
                new BadPenguin("Tux")   // ← time bomb
        );

        System.out.println("Calling fly() on all BadBirds...");
        for (BadBird b : birds) {
            try {
                b.fly();
            } catch (UnsupportedOperationException e) {
                System.out.println("  RUNTIME CRASH on " + b.name + ": " + e.getMessage());
                System.out.println("  → Code compiled fine, but FAILS at runtime — LSP violation!");
            }
        }

        // ─── GOOD: Segregated contracts — no unexpected exceptions ─────────
        System.out.println("\n--- GOOD: Correct hierarchy — contracts are always honoured ---");

        List<FlyingBehavior>   flyers   = List.of(new Eagle(), new Duck());
        List<SwimmingBehavior> swimmers = List.of(new Penguin(), new Duck());

        System.out.println("All flyers:");
        flyers.forEach(f -> {
            f.fly();
            System.out.println("    max altitude: " + f.maxAltitudeMeters() + "m");
        });

        System.out.println("All swimmers:");
        swimmers.forEach(s -> {
            s.swim();
            System.out.println("    max speed: " + s.maxSpeedKnots() + " knots");
        });

        System.out.println("\nKey insight: makeAllFlyingBirdsFly(List<FlyingBehavior>) NEVER throws.");
        System.out.println("  Penguin is not in the flyers list — it correctly implements only SwimmingBehavior.");
    }
}
