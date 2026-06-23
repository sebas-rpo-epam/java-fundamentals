package org.epamcampus.dp.structural.decorator.impl;

import org.epamcampus.dp.structural.decorator.pattern.OrderRepository;
import org.epamcampus.dp.structural.decorator.domain.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class CachingOrderRepository implements OrderRepository {

    private final OrderRepository delegate;
    private final Map<UUID, Order> cache = new HashMap<>();

    public CachingOrderRepository(OrderRepository delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate must not be null");
    }

    @Override
    public Optional<Order> findById(UUID id) {
        if (cache.containsKey(id)) {
            System.out.println("  [CACHE] HIT for id=" + id);
            return Optional.of(cache.get(id));
        }
        System.out.println("  [CACHE] MISS for id=" + id);
        Optional<Order> result = delegate.findById(id);
        result.ifPresent(o -> cache.put(o.id(), o));
        return result;
    }

    @Override
    public Order save(Order order) {
        Order saved = delegate.save(order);
        cache.put(saved.id(), saved); // ✅ keep cache consistent
        return saved;
    }

    @Override
    public List<Order> findAll() {
        return delegate.findAll();
    }
}
