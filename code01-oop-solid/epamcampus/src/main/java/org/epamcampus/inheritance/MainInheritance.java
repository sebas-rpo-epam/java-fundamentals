package org.epamcampus.inheritance;

import java.math.BigDecimal;
import java.util.List;

public class MainInheritance {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          INHERITANCE DEMO                ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // sealed class: the compiler KNOWS all possible subtypes
        List<Employee> employees = List.of(
                new FullTimeEmployee("E001", "Alice Smith",  "Engineering", new BigDecimal("84000.00")),
                new ContractEmployee("E002", "Bob Jones",    "Design",      new BigDecimal("75.00"), 160),
                new InternEmployee  ("E003", "Carol White",  "QA")
        );

        System.out.println("\n--- Payslips (Template Method pattern via generateFullPayslip()) ---");
        // Dynamic dispatch: each call routes to the correct calculateMonthlySalary()
        employees.forEach(e -> System.out.println(e.generateFullPayslip()));

        System.out.println("--- Pattern matching with sealed class (exhaustive switch) ---");
        for (Employee e : employees) {
            // The compiler verifies ALL permitted subtypes are handled — no default needed
            String desc = switch (e) {
                case FullTimeEmployee fte  -> "Full-time, annual $" + fte.calculateMonthlySalary().multiply(new BigDecimal("12"));
                case ContractEmployee ce   -> "Contractor, this month $" + ce.calculateMonthlySalary();
                case InternEmployee   ie   -> "Intern, stipend $" + ie.calculateMonthlySalary();
            };
            System.out.println("  " + e.getName() + ": " + desc);
        }
    }
}
