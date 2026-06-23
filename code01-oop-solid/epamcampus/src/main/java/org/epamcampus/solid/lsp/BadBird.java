package org.epamcampus.solid.lsp;

// ❌ Base class implies ALL birds can fly — but that's not true
public abstract class BadBird {
    public String name;

    public BadBird(String name) { this.name = name; }

    public abstract void fly();
}
