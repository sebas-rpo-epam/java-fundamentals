package org.epamcampus.composition;

public record BowAttack() implements AttackBehavior {
    @Override public AttackResult attack(String attacker, String target) {
        return new AttackResult(attacker, target, damagePoints(), "fires an arrow");
    }
    @Override public int damagePoints() { return 30; }
}
