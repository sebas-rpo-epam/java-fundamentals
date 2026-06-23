package org.epamcampus.dp.structural.decorator.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Order(UUID id, String customerId, BigDecimal total, String status) {}
