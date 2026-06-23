package org.epamcampus.composition;

public interface AttackBehavior {
    AttackResult attack(String attackerName, String target);
    int damagePoints();
}
