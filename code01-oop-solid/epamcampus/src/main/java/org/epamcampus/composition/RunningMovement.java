package org.epamcampus.composition;

public record RunningMovement() implements MovementBehavior {
    @Override public void move(String name) {
        System.out.println("  " + name + " sprints across the ground");
    }
    @Override public int movementSpeedKmh() { return 35; }
}
