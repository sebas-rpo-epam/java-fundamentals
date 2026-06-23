package org.epamcampus.dp.behavioral.command.demo;

import org.epamcampus.dp.behavioral.command.bad.BadBankController;
import org.epamcampus.dp.behavioral.command.domain.BankAccount;
import org.epamcampus.dp.behavioral.command.domain.CommandHistory;
import org.epamcampus.dp.behavioral.command.impl.DepositCommand;
import org.epamcampus.dp.behavioral.command.impl.WithdrawCommand;
import org.epamcampus.dp.behavioral.command.impl.TransferCommand;

import java.math.BigDecimal;

public class MainCommand {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          PATTERN 6: COMMAND              ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n--- BAD: Direct calls — no undo, no audit ---");
        BankAccount badAlice = new BankAccount("bad-alice", new BigDecimal("1000"));
        BankAccount badBob   = new BankAccount("bad-bob",   new BigDecimal("500"));
        BadBankController bad = new BadBankController();
        bad.transferFunds(badAlice, badBob, new BigDecimal("150"));
        System.out.println("  After transfer: " + badAlice + ", " + badBob);
        System.out.println("  (Cannot undo — no history recorded)");

        System.out.println("\n--- GOOD: CommandHistory with full undo support ---");
        BankAccount alice = new BankAccount("alice", new BigDecimal("1000"));
        BankAccount bob   = new BankAccount("bob",   new BigDecimal("500"));
        CommandHistory history = new CommandHistory();

        System.out.println("\n  Initial: " + alice + ", " + bob);

        System.out.println("\n  [Executing commands...]");
        history.execute(new DepositCommand(alice, new BigDecimal("200")));
        history.execute(new WithdrawCommand(alice, new BigDecimal("300")));
        history.execute(new TransferCommand(alice, bob, new BigDecimal("150")));

        System.out.println("\n  After all operations:");
        System.out.println("    " + alice);
        System.out.println("    " + bob);

        System.out.println("\n  [Undoing last 2 operations...]");
        history.undoLast(); // undo transfer
        history.undoLast(); // undo withdraw

        System.out.println("\n  After 2 undos:");
        System.out.println("    " + alice);
        System.out.println("    " + bob);

        System.out.println("\n  [Remaining history:]");
        history.printHistory();

        System.out.println("\nKey insight: Commands are first-class objects — they can be stored, queued," +
                " replayed, and undone. The CommandHistory is the invoker and audit log in one.");
    }
}
