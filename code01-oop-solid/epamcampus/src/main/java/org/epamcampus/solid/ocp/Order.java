package org.epamcampus.solid.ocp;

import java.math.BigDecimal;
import java.util.UUID;

public record Order(UUID id, BigDecimal total) {}
