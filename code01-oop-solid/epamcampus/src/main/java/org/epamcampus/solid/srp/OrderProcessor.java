package org.epamcampus.solid.srp;

import java.math.BigDecimal;

// ✅ SRP: ONE responsibility — orchestrate the order processing flow
// It does NOT know how to persist, send emails, or generate reports.
// Each collaborator has its own single responsibility.
public class OrderProcessor {

    private final OrderRepository orderRepository;
    private final NotificationService notificationService;
    private final ReportService reportService;

    public OrderProcessor(OrderRepository orderRepository,
                          NotificationService notificationService,
                          ReportService reportService) {
        this.orderRepository     = orderRepository;
        this.notificationService = notificationService;
        this.reportService       = reportService;
    }

    public Order process(Order order) {
        validateOrder(order);                                  // business rule — stays here
        Order saved = orderRepository.save(order);            // delegated
        notificationService.sendOrderConfirmation(saved);     // delegated
        reportService.generateOrderReport(saved);             // delegated
        return saved;
    }

    private void validateOrder(Order order) {
        if (order.items().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        if (order.calculateTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Order total must be positive");
        }
    }
}
