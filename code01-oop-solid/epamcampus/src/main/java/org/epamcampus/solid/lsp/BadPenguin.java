package org.epamcampus.solid.lsp;

// ❌ VIOLATES LSP — Penguin IS-A BadBird in the hierarchy,
// but it CANNOT fulfil the fly() contract → substitution breaks the program
public class BadPenguin extends BadBird {

    public BadPenguin(String name) { super(name); }

    @Override
    public void fly() {
        // Caller expects this to work — but it throws at runtime!
        throw new UnsupportedOperationException(name + " cannot fly! (Liskov violation)");
    }
}
