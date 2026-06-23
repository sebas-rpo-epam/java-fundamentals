package org.epamcampus.solid.lsp;

// ✅ Penguin only implements SwimmingBehavior — no fly() contract to violate
public class Penguin implements SwimmingBehavior {
    @Override public void swim()               { System.out.println("  Penguin glides underwater"); }
    @Override public double maxSpeedKnots()    { return 15.0; }
}
