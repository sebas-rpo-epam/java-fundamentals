package org.epamcampus.dp.behavioral.command.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {

    private final String id;
    private BigDecimal balance;

    public BankAccount(String id, BigDecimal initialBalance) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.balance = Objects.requireNonNull(initialBalance, "initialBalance must not be null");
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) > 0) {
            throw new IllegalStateException("Insufficient funds in account " + id);
        }
        balance = balance.subtract(amount);
    }

    public BigDecimal getBalance() { return balance; }
    public String getId()          { return id; }

    @Override
    public String toString() {
        return "Account[" + id + " balance=$" + balance + "]";
    }
}
