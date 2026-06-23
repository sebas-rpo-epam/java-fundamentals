package org.epamcampus.dp.behavioral.command.impl;

import org.epamcampus.dp.behavioral.command.pattern.Command;
import org.epamcampus.dp.behavioral.command.domain.BankAccount;

import java.math.BigDecimal;
import java.util.Objects;

public class WithdrawCommand implements Command {

    private final BankAccount account;
    private final BigDecimal amount;

    public WithdrawCommand(BankAccount account, BigDecimal amount) {
        this.account = Objects.requireNonNull(account, "account must not be null");
        this.amount  = Objects.requireNonNull(amount, "amount must not be null");
    }

    @Override
    public void execute() { account.withdraw(amount); }

    @Override
    public void undo()    { account.deposit(amount); }

    @Override
    public String describe() {
        return "Withdraw $" + amount + " from " + account.getId();
    }
}
