package org.epamcampus.composition;

public record SpellAttack() implements AttackBehavior {
    @Override public AttackResult attack(String attacker, String target) {
        return new AttackResult(attacker, target, damagePoints(), "casts a fireball");
    }
    @Override public int damagePoints() { return 80; }
}
