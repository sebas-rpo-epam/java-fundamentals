package org.epamcampus.encapsulation;

import java.math.BigDecimal;

public class MainEncapsulation {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         ENCAPSULATION DEMO               ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ─── BAD: Anemic class — anyone can corrupt state ─────────────────
        System.out.println("\n--- BAD: BadBankAccount (anemic, no protection) ---");
        BadBankAccount bad = new BadBankAccount();
        bad.accountID = "ACC-001";
        bad.ownerId  = "user-42";
        bad.balance  = 1000.0;
        System.out.println("Initial balance: $" + bad.balance);

        // Direct field mutation — no validation, no history, no invariants
        bad.balance = -999_999;   // This should be IMPOSSIBLE in real code!
        System.out.println("After direct mutation: $" + bad.balance + "  ← CORRUPTED STATE!");

        // ─── GOOD: Encapsulated BankAccount ───────────────────────────────
        System.out.println("\n--- GOOD: BankAccount (encapsulated, invariants enforced) ---");
        BankAccount account = new BankAccount("ACC-002", "user-99", new BigDecimal("500.00"));
        System.out.println("Created account: " + account.getAccountId() + " | Balance: $" + account.getBalance());

        account.deposit(new BigDecimal("200.00"));
        System.out.println("After deposit $200: $" + account.getBalance());

        account.withdraw(new BigDecimal("100.00"));
        System.out.println("After withdrawal $100: $" + account.getBalance());

        System.out.println("\nTransaction history:");
        account.getHistory().forEach(t -> System.out.println("  " + t));

        // Defensive copy: mutating the returned list does NOT affect internal state
        try {
            account.getHistory().add(new Transaction(TransactionType.DEPOSIT,
                    BigDecimal.TEN, java.time.LocalDateTime.now()));
        } catch (UnsupportedOperationException e) {
            System.out.println("\nDefensive copy works — external mutation blocked: " + e.getClass().getSimpleName());
        }

        // Business rule: cannot overdraft
        System.out.println("\n--- Testing overdraft protection ---");
        try {
            account.withdraw(new BigDecimal("10000.00"));
        } catch (InsufficientFundsException e) {
            System.out.println("Overdraft blocked: " + e.getMessage());
        }

        // Business rule: negative amounts rejected
        System.out.println("\n--- Testing negative amount protection ---");
        try {
            account.deposit(new BigDecimal("-50.00"));
        } catch (IllegalArgumentException e) {
            System.out.println("Negative deposit blocked: " + e.getMessage());
        }
    }
}
