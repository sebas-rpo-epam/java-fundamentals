package org.epamcampus.ioc;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

// @Service  ← in Spring, this tells the IoC container: "manage this bean"
// Constructor injection: Spring (or us) provides all deps — no new() calls here
public class ProductOrderService {

    private final ProductRepository productRepository;
    private final PricingStrategy   pricingStrategy;
    private final InventoryService  inventoryService;

    // Since Spring 4.3: single-constructor injection is implicit — no @Autowired needed
    public ProductOrderService(ProductRepository productRepository,
                               PricingStrategy pricingStrategy,
                               InventoryService inventoryService) {
        this.productRepository = Objects.requireNonNull(productRepository);
        this.pricingStrategy   = Objects.requireNonNull(pricingStrategy);
        this.inventoryService  = Objects.requireNonNull(inventoryService);
    }

    public OrderConfirmation placeOrder(UUID productId, int quantity, Customer customer) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        if (!inventoryService.isAvailable(productId, quantity)) {
            throw new RuntimeException("Insufficient stock for product: " + product.name());
        }

        BigDecimal unitPrice = pricingStrategy.calculatePrice(product, customer);
        BigDecimal total     = unitPrice.multiply(new BigDecimal(quantity));

        inventoryService.reserve(productId, quantity);

        return new OrderConfirmation(UUID.randomUUID(), product, quantity, total);
    }
}
