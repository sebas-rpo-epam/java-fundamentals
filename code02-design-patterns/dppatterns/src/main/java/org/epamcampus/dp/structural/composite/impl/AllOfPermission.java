package org.epamcampus.dp.structural.composite.impl;

import org.epamcampus.dp.structural.composite.pattern.Permission;
import org.epamcampus.dp.structural.composite.domain.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AllOfPermission implements Permission {

    private final String name;
    private final List<Permission> requirements;

    public AllOfPermission(String name, List<Permission> requirements) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.requirements = List.copyOf(Objects.requireNonNull(requirements, "requirements must not be null"));
    }

    @Override
    public boolean isGranted(User user) {
        return requirements.stream().allMatch(p -> p.isGranted(user));
    }

    @Override
    public String describe() {
        String children = requirements.stream()
                .map(Permission::describe)
                .collect(Collectors.joining(", "));
        return name + " [ALL OF: " + children + "]";
    }
}
