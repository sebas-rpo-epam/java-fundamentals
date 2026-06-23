package org.epamcampus.solid.isp;

// ✅ Advanced device: implements exactly the interfaces it supports
public class OfficePrinterPro implements Printable, Scannable, Faxable, Copyable {

    @Override public void print(Document doc) {
        System.out.println("  [OfficePrinterPro] Printing: " + doc.title());
    }
    @Override public void scan(Document doc) {
        System.out.println("  [OfficePrinterPro] Scanning: " + doc.title());
    }
    @Override public void fax(Document doc, String dest) {
        System.out.println("  [OfficePrinterPro] Faxing '" + doc.title() + "' to " + dest);
    }
    @Override public void copy(Document doc, int copies) {
        System.out.println("  [OfficePrinterPro] Copying " + copies + "x: " + doc.title());
    }
}
