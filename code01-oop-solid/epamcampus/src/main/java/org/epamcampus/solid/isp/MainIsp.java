package org.epamcampus.solid.isp;

public class MainIsp {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       SOLID — I: Interface Segregation   ║");
        System.out.println("╚══════════════════════════════════════════╝");

        Document report = new Document("Q2 Report", "Revenue data...");

        // ─── BAD: fat interface forces UnsupportedOperationException ───────
        System.out.println("\n--- BAD: BadSimplePrinter forced to implement scan/fax/copy ---");
        BadSimplePrinter bad = new BadSimplePrinter();
        bad.print(report);  // this works
        try {
            bad.scan(report);  // CRASH — this is also a LSP violation
        } catch (UnsupportedOperationException e) {
            System.out.println("  CRASH on scan(): " + e.getMessage());
        }
        try {
            bad.fax(report, "+1-800-555-0000");
        } catch (UnsupportedOperationException e) {
            System.out.println("  CRASH on fax():  " + e.getMessage());
        }

        // ─── GOOD: small interfaces, each class implements what it can ─────
        System.out.println("\n--- GOOD: Segregated interfaces ---");

        SimplePrinter    simple  = new SimplePrinter();
        OfficePrinterPro office  = new OfficePrinterPro();

        System.out.println("SimplePrinter (only Printable):");
        simple.print(report);

        System.out.println("OfficePrinterPro (Printable + Scannable + Faxable + Copyable):");
        office.print(report);
        office.scan(report);
        office.fax(report, "+1-800-555-0000");
        office.copy(report, 3);

        System.out.println("\nDocumentPrintingJob works with ANY Printable:");
        new DocumentPrintingJob(simple).execute(report);
        new DocumentPrintingJob(office).execute(report);

        System.out.println("\nKey insight: DocumentPrintingJob only knows about print().");
        System.out.println("  It NEVER breaks when OfficePrinterPro gains new capabilities.");
    }
}
