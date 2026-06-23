package org.epamcampus.abstraction;

import java.util.UUID;

public record User(UUID id, String name, String email, String department) {}
