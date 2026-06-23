package org.epamcampus.composition;

public record SwimmingMovement() implements MovementBehavior {
    @Override public void move(String name) {
        System.out.println("  " + name + " glides through the water");
    }
    @Override public int movementSpeedKmh() { return 40; }
}
