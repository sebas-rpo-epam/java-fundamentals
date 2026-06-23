package org.epamcampus.solid.srp;

import java.util.*;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<UUID, Order> store = new HashMap<>();

    @Override
    public Order save(Order order) {
        store.put(order.id(), order);
        System.out.println("  [OrderRepository] Saved order " + order.id() + " total=$" + order.calculateTotal());
        return order;
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}
