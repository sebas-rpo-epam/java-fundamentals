package org.epamcampus.dp.structural.composite.impl;

import org.epamcampus.dp.structural.composite.pattern.Permission;
import org.epamcampus.dp.structural.composite.domain.User;

import java.util.Objects;
import java.util.function.Predicate;

public class SimplePermission implements Permission {

    private final String name;
    private final Predicate<User> check;

    public SimplePermission(String name, Predicate<User> check) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.check = Objects.requireNonNull(check, "check must not be null");
    }

    @Override
    public boolean isGranted(User user) {
        return check.test(user);
    }

    @Override
    public String describe() {
        return name;
    }
}
