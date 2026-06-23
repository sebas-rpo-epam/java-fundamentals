package org.epamcampus.solid.dip;

public record User(String id, String email, String passwordHash, String role) {}
