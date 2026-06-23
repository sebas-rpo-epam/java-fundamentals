package org.epamcampus.dp.structural.decorator.impl;

import org.epamcampus.dp.structural.decorator.pattern.OrderRepository;
import org.epamcampus.dp.structural.decorator.domain.Order;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class LoggingOrderRepository implements OrderRepository {

    private final OrderRepository delegate;

    public LoggingOrderRepository(OrderRepository delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate must not be null");
    }

    @Override
    public Optional<Order> findById(UUID id) {
        System.out.println("  [LOG] Calling findById(" + id + ")...");
        Optional<Order> result = delegate.findById(id);
        System.out.println("  [LOG] findById returned: " + result);
        return result;
    }

    @Override
    public Order save(Order order) {
        System.out.println("  [LOG] Calling save(order.id=" + order.id() + ")...");
        Order result = delegate.save(order);
        System.out.println("  [LOG] save returned: " + result.id());
        return result;
    }

    @Override
    public List<Order> findAll() {
        System.out.println("  [LOG] Calling findAll()...");
        List<Order> result = delegate.findAll();
        System.out.println("  [LOG] findAll returned " + result.size() + " orders");
        return result;
    }
}
