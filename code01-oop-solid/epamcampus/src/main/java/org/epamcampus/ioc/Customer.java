package org.epamcampus.ioc;

import java.util.UUID;

public record Customer(UUID id, String name, CustomerTier tier) {

    public static Customer premium(String name)  { return new Customer(UUID.randomUUID(), name, CustomerTier.PREMIUM);  }
    public static Customer standard(String name) { return new Customer(UUID.randomUUID(), name, CustomerTier.STANDARD); }
    public static Customer guest(String name)    { return new Customer(UUID.randomUUID(), name, CustomerTier.GUEST);    }
}
