package org.epamcampus.dp.behavioral.command.bad;

import org.epamcampus.dp.behavioral.command.domain.BankAccount;

import java.math.BigDecimal;

/**
 * ❌ PROBLEM: Direct calls — no undo, no audit trail, no queueing possible.
 * A bug in the withdrawal leaves the system in an inconsistent state with no recovery.
 */
public class BadBankController {

    public void transferFunds(BankAccount from, BankAccount to, BigDecimal amount) {
        // ❌ No undo possible — if deposit crashes after withdraw, money is lost
        from.withdraw(amount);
        // ❌ If an exception happens here, 'from' has been debited but 'to' never credited
        to.deposit(amount);
        // ❌ No audit trail — nothing was recorded
    }
}
