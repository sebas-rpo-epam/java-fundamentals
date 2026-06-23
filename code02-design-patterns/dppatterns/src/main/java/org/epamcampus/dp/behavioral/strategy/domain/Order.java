package org.epamcampus.dp.behavioral.strategy.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Order(UUID id, BigDecimal weightKg, String destinationCountry, BigDecimal subtotal) {}
