package org.epamcampus.composition;

public record FlyingMovement() implements MovementBehavior {
    @Override public void move(String name) {
        System.out.println("  " + name + " soars through the sky");
    }
    @Override public int movementSpeedKmh() { return 120; }
}
