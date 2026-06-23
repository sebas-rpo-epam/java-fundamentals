package org.epamcampus.dp.structural.composite.domain;

import java.util.Set;

public record User(String id, String name, Set<String> roles, Set<String> directPermissions) {}
