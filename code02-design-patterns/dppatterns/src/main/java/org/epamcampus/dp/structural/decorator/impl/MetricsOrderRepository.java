package org.epamcampus.dp.structural.decorator.impl;

import org.epamcampus.dp.structural.decorator.pattern.OrderRepository;
import org.epamcampus.dp.structural.decorator.domain.Order;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MetricsOrderRepository implements OrderRepository {

    private final OrderRepository delegate;
    private int findByIdCount = 0;
    private int saveCount = 0;
    private int findAllCount = 0;
    private final AtomicLong totalTimeNs = new AtomicLong(0);

    public MetricsOrderRepository(OrderRepository delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate must not be null");
    }

    @Override
    public Optional<Order> findById(UUID id) {
        long start = System.nanoTime();
        Optional<Order> result = delegate.findById(id);
        long elapsed = System.nanoTime() - start;
        totalTimeNs.addAndGet(elapsed);
        findByIdCount++;
        System.out.printf("  [METRICS] findById took %.2fms (call #%d)%n",
                elapsed / 1_000_000.0, findByIdCount);
        return result;
    }

    @Override
    public Order save(Order order) {
        long start = System.nanoTime();
        Order result = delegate.save(order);
        long elapsed = System.nanoTime() - start;
        totalTimeNs.addAndGet(elapsed);
        saveCount++;
        System.out.printf("  [METRICS] save took %.2fms (call #%d)%n",
                elapsed / 1_000_000.0, saveCount);
        return result;
    }

    @Override
    public List<Order> findAll() {
        long start = System.nanoTime();
        List<Order> result = delegate.findAll();
        long elapsed = System.nanoTime() - start;
        totalTimeNs.addAndGet(elapsed);
        findAllCount++;
        System.out.printf("  [METRICS] findAll took %.2fms (call #%d)%n",
                elapsed / 1_000_000.0, findAllCount);
        return result;
    }

    public void printSummary() {
        System.out.printf("  [METRICS] Summary: findById=%d, save=%d, findAll=%d, totalTime=%.2fms%n",
                findByIdCount, saveCount, findAllCount, totalTimeNs.get() / 1_000_000.0);
    }
}
