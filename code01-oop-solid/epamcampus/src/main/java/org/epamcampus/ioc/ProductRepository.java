package org.epamcampus.ioc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> findById(UUID id);
    List<Product> findByCategory(String category);
    Product save(Product product);
}
