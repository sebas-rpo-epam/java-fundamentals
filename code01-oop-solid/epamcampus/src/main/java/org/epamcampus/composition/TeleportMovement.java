package org.epamcampus.composition;

public record TeleportMovement() implements MovementBehavior {
    @Override public void move(String name) {
        System.out.println("  " + name + " vanishes and reappears instantly");
    }
    @Override public int movementSpeedKmh() { return Integer.MAX_VALUE; }
}
