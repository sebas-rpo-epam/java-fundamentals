package org.epamcampus.solid.lsp;

// ✅ Correct contract: only things that CAN fly implement this
public interface FlyingBehavior {
    void fly();
    int maxAltitudeMeters();
}
