package org.epamcampus.df.json.basic;

import java.math.BigDecimal;
import java.util.List;

public record ProductCatalog(
    String catalogName,
    String currency,
    List<Product> products
) {
    public record Product(String sku, String name, BigDecimal price, boolean inStock) {}
}
