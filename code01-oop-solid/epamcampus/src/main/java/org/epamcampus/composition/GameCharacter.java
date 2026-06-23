package org.epamcampus.composition;

import java.util.Objects;

// ✅ Composition over Inheritance — GameCharacter HAS-A movement and attack,
// it does NOT IS-A FlyingCharacter or SwimmingCharacter
public class GameCharacter {

    private final String name;
    private MovementBehavior movementBehavior;  // mutable: can change at runtime!
    private AttackBehavior attackBehavior;

    public GameCharacter(String name, MovementBehavior movement, AttackBehavior attack) {
        this.name             = Objects.requireNonNull(name);
        this.movementBehavior = Objects.requireNonNull(movement);
        this.attackBehavior   = Objects.requireNonNull(attack);
    }

    public void move() {
        movementBehavior.move(name);
        System.out.println("    speed: " + movementBehavior.movementSpeedKmh() + " km/h");
    }

    public AttackResult attack(String target) {
        return attackBehavior.attack(name, target);
    }

    // Strategy swap at runtime — no class hierarchy change needed
    public void setMovementBehavior(MovementBehavior newBehavior) {
        System.out.println("  " + name + " learned a new movement: "
                + newBehavior.getClass().getSimpleName());
        this.movementBehavior = Objects.requireNonNull(newBehavior);
    }

    public void setAttackBehavior(AttackBehavior newBehavior) {
        System.out.println("  " + name + " equipped a new attack: "
                + newBehavior.getClass().getSimpleName());
        this.attackBehavior = Objects.requireNonNull(newBehavior);
    }

    public String getName() { return name; }
}
