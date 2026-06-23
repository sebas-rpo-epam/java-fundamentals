package org.epamcampus.dp.behavioral.command.impl;

import org.epamcampus.dp.behavioral.command.pattern.Command;
import org.epamcampus.dp.behavioral.command.domain.BankAccount;

import java.math.BigDecimal;
import java.util.Objects;

public class DepositCommand implements Command {

    private final BankAccount account;
    private final BigDecimal amount;

    public DepositCommand(BankAccount account, BigDecimal amount) {
        this.account = Objects.requireNonNull(account, "account must not be null");
        this.amount  = Objects.requireNonNull(amount, "amount must not be null");
    }

    @Override
    public void execute() { account.deposit(amount); }

    @Override
    public void undo()    { account.withdraw(amount); }

    @Override
    public String describe() {
        return "Deposit $" + amount + " to " + account.getId();
    }
}
