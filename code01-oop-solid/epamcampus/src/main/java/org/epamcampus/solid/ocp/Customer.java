package org.epamcampus.solid.ocp;

public record Customer(String id, String name, CustomerType type, int totalPurchases) {}
