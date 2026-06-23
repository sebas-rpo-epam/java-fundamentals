package org.epamcampus.abstraction;

import java.util.List;
import java.util.UUID;

public class MainAbstraction {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║          ABSTRACTION DEMO                ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ─── Interface: UserRepository ─────────────────────────────────────
        System.out.println("\n--- Interface: program against the contract, not the implementation ---");

        // We declare the variable as the INTERFACE type — could swap to JpaUserRepository
        UserRepository repo = new InMemoryUserRepository();

        UUID aliceId = UUID.randomUUID();
        repo.save(new User(aliceId,      "Alice Smith", "alice@epam.com", "Engineering"));
        repo.save(new User(UUID.randomUUID(), "Bob Jones",  "bob@epam.com",   "Design"));
        repo.save(new User(UUID.randomUUID(), "Carol White","carol@epam.com",  "Engineering"));

        System.out.println("Engineering team:");
        repo.findByDepartment("Engineering")
                .forEach(u -> System.out.println("  → " + u.name() + " <" + u.email() + ">"));

        System.out.println("findByIdOrThrow (default method): " + repo.findByIdOrThrow(aliceId).name());
        System.out.println("existsByEmail: " + repo.existsByEmail("bob@epam.com"));

        try {
            repo.findByIdOrThrow(UUID.randomUUID()); // unknown id
        } catch (RuntimeException e) {
            System.out.println("findByIdOrThrow on unknown id: " + e.getMessage());
        }

        // ─── Abstract class: Template Method pattern ───────────────────────
        System.out.println("\n--- Abstract class: Template Method Pattern ---");
        ReportConfiguration config = new ReportConfiguration("Monthly Report", "EPAM Campus");
        ReportData data = new ReportData("Q2 Results",
                List.of("Revenue: $1,200,000", "Expenses: $850,000", "Net Profit: $350,000"));

        // Both generators share: validateData() + buildFooter() from ReportGenerator
        // Each provides its own: buildHeader(), buildBody(), assembleReport()
        ReportGenerator textGen = new TextReportGenerator(config);
        ReportGenerator csvGen  = new CsvReportGenerator(config);

        System.out.println("\n" + textGen.generate(data));
        System.out.println("\n" + csvGen.generate(data));

        System.out.println("\nKey insight: generate() is final — the algorithm cannot be overridden.");
        System.out.println("  Subclasses only fill in the variable steps. That's the Template Method Pattern.");
    }
}
