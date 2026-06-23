package org.epamcampus.ioc;

import java.math.BigDecimal;
import java.util.UUID;

public record Product(UUID id, String name, BigDecimal basePrice, int stockQuantity) {}
