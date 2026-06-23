package org.epamcampus.dp.structural.decorator.impl;

import org.epamcampus.dp.structural.decorator.pattern.OrderRepository;
import org.epamcampus.dp.structural.decorator.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryOrderRepository implements OrderRepository {

    private final Map<UUID, Order> store = new HashMap<>();

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Order save(Order order) {
        store.put(order.id(), order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }
}
