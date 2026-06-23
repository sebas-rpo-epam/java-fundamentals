package org.epamcampus.ioc;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<UUID, Product> store = new HashMap<>();

    public InMemoryProductRepository(Product... products) {
        for (Product p : products) store.put(p.id(), p);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> findByCategory(String category) {
        return List.copyOf(store.values());
    }

    @Override
    public Product save(Product product) {
        store.put(product.id(), product);
        return product;
    }
}
