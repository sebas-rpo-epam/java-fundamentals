package org.epamcampus.composition;

public class MainComposition {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║      COMPOSITION OVER INHERITANCE        ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n--- Problem with inheritance: combinatorial explosion ---");
        System.out.println("  If you need Flying+Sword, Swimming+Spell, Flying+Swimming+Bow...");
        System.out.println("  ...you'd need a separate subclass for EVERY combination.");
        System.out.println("  3 movements × 3 attacks = 9 subclasses minimum. Add 1 movement → 12.");

        System.out.println("\n--- Solution: composition (HAS-A, not IS-A) ---");

        // Warrior: starts swimming, uses sword
        GameCharacter warrior = new GameCharacter("Warrior", new SwimmingMovement(), new SwordAttack());
        System.out.println("\nWarrior initial state:");
        warrior.move();
        System.out.println("  " + warrior.attack("Dragon"));

        // Wizard: teleports, uses spells
        GameCharacter wizard = new GameCharacter("Wizard", new TeleportMovement(), new SpellAttack());
        System.out.println("\nWizard:");
        wizard.move();
        System.out.println("  " + wizard.attack("Orc"));

        // Ranger: runs, uses bow
        GameCharacter ranger = new GameCharacter("Ranger", new RunningMovement(), new BowAttack());
        System.out.println("\nRanger:");
        ranger.move();
        System.out.println("  " + ranger.attack("Wolf"));

        // ─── Runtime behaviour swap — impossible with simple inheritance ───
        System.out.println("\n--- Runtime strategy swap (impossible with plain inheritance) ---");
        System.out.println("Warrior falls into a volcano area — needs to fly now:");
        warrior.setMovementBehavior(new FlyingMovement());
        warrior.move();

        System.out.println("\nWizard finds a legendary bow mid-battle:");
        wizard.setAttackBehavior(new BowAttack());
        System.out.println("  " + wizard.attack("Troll"));

        System.out.println("\nKey insight: GameCharacter class NEVER changed.");
        System.out.println("  Zero new subclasses. New combos = new behavior instances.");
    }
}
