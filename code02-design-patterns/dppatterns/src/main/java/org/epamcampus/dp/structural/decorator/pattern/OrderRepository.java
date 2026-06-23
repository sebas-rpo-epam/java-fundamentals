package org.epamcampus.dp.structural.decorator.pattern;

import org.epamcampus.dp.structural.decorator.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Order> findById(UUID id);
    Order save(Order order);
    List<Order> findAll();
}
