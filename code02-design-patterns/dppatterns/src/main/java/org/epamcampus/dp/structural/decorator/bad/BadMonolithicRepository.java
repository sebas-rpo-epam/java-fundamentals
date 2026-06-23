package org.epamcampus.dp.structural.decorator.bad;

import org.epamcampus.dp.structural.decorator.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * ❌ PROBLEM: Persistence + logging + caching + metrics in one class.
 * Violates SRP. Cannot disable caching without touching persistence.
 * Cannot swap logger without touching this class.
 * This is what Spring AOP @Cacheable + @Transactional solve transparently.
 */
public class BadMonolithicRepository {

    private final Map<UUID, Order> store = new HashMap<>();
    private final Map<UUID, Order> cache = new HashMap<>(); // ❌ mixed with persistence
    private int callCount = 0;                               // ❌ mixed with persistence

    public Optional<Order> findById(UUID id) {
        callCount++;
        long start = System.nanoTime(); // ❌ metrics hardcoded here

        // ❌ logging hardcoded here
        System.out.println("  [BAD] Calling findById — all concerns tangled together");

        // ❌ caching hardcoded here
        if (cache.containsKey(id)) {
            return Optional.of(cache.get(id));
        }

        Optional<Order> result = Optional.ofNullable(store.get(id));
        result.ifPresent(o -> cache.put(o.id(), o));

        long elapsed = System.nanoTime() - start;
        System.out.printf("  [BAD] findById took %.2fms (call #%d)%n", elapsed / 1_000_000.0, callCount);
        return result;
    }

    public Order save(Order order) {
        store.put(order.id(), order);
        cache.put(order.id(), order); // ❌ cache + persistence tangled
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }
}
