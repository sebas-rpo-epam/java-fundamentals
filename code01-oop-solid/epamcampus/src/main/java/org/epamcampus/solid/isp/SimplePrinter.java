package org.epamcampus.solid.isp;

// ✅ Implements only what it can do — no fake methods, no exceptions
public class SimplePrinter implements Printable {

    @Override
    public void print(Document doc) {
        System.out.println("  [SimplePrinter] Printing: " + doc.title());
    }
}
