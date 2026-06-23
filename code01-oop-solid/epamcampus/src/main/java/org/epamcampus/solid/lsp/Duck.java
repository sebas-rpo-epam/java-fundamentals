package org.epamcampus.solid.lsp;

// ✅ Duck implements BOTH — genuinely satisfies both contracts
public class Duck implements FlyingBehavior, SwimmingBehavior {
    @Override public void fly()               { System.out.println("  Duck flaps and flies low"); }
    @Override public int maxAltitudeMeters()  { return 500; }
    @Override public void swim()              { System.out.println("  Duck paddles on the surface"); }
    @Override public double maxSpeedKnots()   { return 3.0; }
}
