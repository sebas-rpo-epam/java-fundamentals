package org.epamcampus.encapsulation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BankAccount {

    private final String accountId;
    private final String ownerId;
    private BigDecimal balance;
    private final List<Transaction> history = new ArrayList<>();

    public BankAccount(String accountId, String ownerId, BigDecimal initialBalance) {
        Objects.requireNonNull(accountId, "accountId cannot be null");
        Objects.requireNonNull(ownerId, "ownerId cannot be null");

        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        this.accountId = accountId;
        this.ownerId = ownerId;
        this.balance = initialBalance;
    }

    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        this.balance = this.balance.add(amount);
        history.add(new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now()));
    }

    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        if (amount.compareTo(this.balance) > 0) {
            throw new InsufficientFundsException(accountId, amount, balance);
        }
        this.balance = this.balance.subtract(amount);
        history.add(new Transaction(TransactionType.WITHDRAWAL, amount, LocalDateTime.now()));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public List<Transaction> getHistory() {
        return Collections.unmodifiableList(history);
    }

    private void validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "Amount cannot be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}
