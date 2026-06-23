package org.epamcampus.solid.isp;

// ✅ Depends only on Printable — not the full fat interface
// Works with SimplePrinter, OfficePrinterPro, or any future Printable
public class DocumentPrintingJob {

    private final Printable printer;

    public DocumentPrintingJob(Printable printer) {
        this.printer = printer;
    }

    public void execute(Document doc) {
        printer.print(doc);
    }
}
