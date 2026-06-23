package org.epamcampus.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SimpleInventoryService implements InventoryService {

    private final Map<UUID, Integer> stock = new HashMap<>();

    public SimpleInventoryService(Product... products) {
        for (Product p : products) stock.put(p.id(), p.stockQuantity());
    }

    @Override
    public boolean isAvailable(UUID productId, int quantity) {
        return stock.getOrDefault(productId, 0) >= quantity;
    }

    @Override
    public void reserve(UUID productId, int quantity) {
        stock.merge(productId, -quantity, Integer::sum);
        System.out.println("  Inventory reserved " + quantity + " units. Remaining: "
                + stock.getOrDefault(productId, 0));
    }
}
