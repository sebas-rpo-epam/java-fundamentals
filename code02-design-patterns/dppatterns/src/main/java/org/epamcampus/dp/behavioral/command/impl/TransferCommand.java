package org.epamcampus.dp.behavioral.command.impl;

import org.epamcampus.dp.behavioral.command.pattern.Command;
import org.epamcampus.dp.behavioral.command.domain.BankAccount;

import java.math.BigDecimal;
import java.util.Objects;

public class TransferCommand implements Command {

    private final WithdrawCommand from;
    private final DepositCommand  to;
    private final BigDecimal      amount;
    private final String          fromId;
    private final String          toId;

    public TransferCommand(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount) {
        Objects.requireNonNull(fromAccount, "fromAccount must not be null");
        Objects.requireNonNull(toAccount,   "toAccount must not be null");
        this.amount  = Objects.requireNonNull(amount, "amount must not be null");
        this.fromId  = fromAccount.getId();
        this.toId    = toAccount.getId();
        this.from    = new WithdrawCommand(fromAccount, amount);
        this.to      = new DepositCommand(toAccount, amount);
    }

    @Override
    public void execute() {
        from.execute();
        to.execute();
    }

    @Override
    public void undo() {
        to.undo();
        from.undo();
    }

    @Override
    public String describe() {
        return "Transfer $" + amount + " from " + fromId + " to " + toId;
    }
}
