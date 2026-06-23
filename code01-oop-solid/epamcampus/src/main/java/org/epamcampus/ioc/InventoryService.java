package org.epamcampus.ioc;

import java.util.UUID;

public interface InventoryService {
    boolean isAvailable(UUID productId, int quantity);
    void reserve(UUID productId, int quantity);
}
