package org.epamcampus.df.jackson.custom;

import com.fasterxml.jackson.databind.annotation.*;
import java.math.BigDecimal;

// Sent over the wire as integer cents (e.g., 1999 = $19.99)
// Our domain uses BigDecimal in dollars
public class Money {
    @JsonDeserialize(using = CentsDeserializer.class)
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal amount;

    public Money() {}
    public Money(BigDecimal amount) { this.amount = amount; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    @Override
    public String toString() { return "Money[$" + amount + "]"; }
}
