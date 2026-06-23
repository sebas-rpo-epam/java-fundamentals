package org.epamcampus.solid.lsp;

public class Eagle implements FlyingBehavior {
    @Override public void fly()              { System.out.println("  Eagle soars at high altitude"); }
    @Override public int maxAltitudeMeters() { return 3000; }
}
