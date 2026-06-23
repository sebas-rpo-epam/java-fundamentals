package org.epamcampus.composition;

public record AttackResult(String attacker, String target, int damage, String description) {
    @Override
    public String toString() {
        return "%s attacks %s for %d damage — %s".formatted(attacker, target, damage, description);
    }
}
