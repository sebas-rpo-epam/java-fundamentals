package org.epamcampus.df.jackson.annotations;

import com.fasterxml.jackson.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

// Demonstrates: @JsonInclude(NON_NULL), @JsonAlias (backward compat), @JsonFormat (date)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    // Accepts both "order_id" (old) and "orderId" (new) when deserializing
    @JsonAlias({"order_id"})
    private String orderId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    private BigDecimal total;

    private String notes; // null -> omitted from JSON output (NON_NULL)

    public OrderResponse() {}
    public OrderResponse(String orderId, LocalDate orderDate, BigDecimal total, String notes) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
        this.notes = notes;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
