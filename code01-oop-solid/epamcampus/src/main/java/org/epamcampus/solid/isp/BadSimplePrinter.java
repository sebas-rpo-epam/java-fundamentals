package org.epamcampus.solid.isp;

// ❌ Forced to implement methods it cannot honour — leads to UnsupportedOperationException
// which ALSO violates LSP (clients that call scan() will crash at runtime)
public class BadSimplePrinter implements BadMultifunctionDevice {

    @Override public void print(Document doc) {
        System.out.println("  [BadSimplePrinter] Printing: " + doc.title());
    }

    @Override public void scan(Document doc) {
        throw new UnsupportedOperationException("This printer cannot scan");
    }

    @Override public void fax(Document doc, String destination) {
        throw new UnsupportedOperationException("This printer cannot fax");
    }

    @Override public void copy(Document doc, int copies) {
        throw new UnsupportedOperationException("This printer cannot copy");
    }

    @Override public void staple(Document doc) {
        throw new UnsupportedOperationException("This printer cannot staple");
    }
}
