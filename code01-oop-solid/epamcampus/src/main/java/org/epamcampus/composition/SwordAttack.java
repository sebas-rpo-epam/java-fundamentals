package org.epamcampus.composition;

public record SwordAttack() implements AttackBehavior {
    @Override public AttackResult attack(String attacker, String target) {
        return new AttackResult(attacker, target, damagePoints(), "slashes with sword");
    }
    @Override public int damagePoints() { return 45; }
}
