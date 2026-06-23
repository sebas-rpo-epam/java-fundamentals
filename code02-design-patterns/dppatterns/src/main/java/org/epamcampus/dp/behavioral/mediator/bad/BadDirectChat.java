package org.epamcampus.dp.behavioral.mediator.bad;

import java.util.ArrayList;
import java.util.List;

/**
 * ❌ PROBLEM: O(N²) coupling — every user knows about every other user.
 * Adding a 5th user requires updating all 4 existing users' reference lists.
 * Users are tightly coupled to each other's implementation details.
 */
public class BadDirectChat {

    static class DirectUser {
        private final String name;
        private final List<DirectUser> peers = new ArrayList<>(); // ❌ knows all others

        DirectUser(String name) { this.name = name; }

        void addPeer(DirectUser user) { peers.add(user); }

        void broadcast(String message) {
            // ❌ Directly calls every peer — adding a 5th user means calling addPeer() on all 4 others
            peers.forEach(p -> System.out.println("  [" + p.name + " received from " + name + "]: " + message));
        }
    }

    public void demonstrate() {
        DirectUser alice = new DirectUser("Alice");
        DirectUser bob   = new DirectUser("Bob");
        DirectUser carol = new DirectUser("Carol");

        // ❌ O(N²) wiring — adding Dave requires: alice.addPeer(dave), bob.addPeer(dave),
        //    carol.addPeer(dave), AND dave.addPeer(alice), dave.addPeer(bob), dave.addPeer(carol)
        alice.addPeer(bob);   alice.addPeer(carol);
        bob.addPeer(alice);   bob.addPeer(carol);
        carol.addPeer(alice); carol.addPeer(bob);

        alice.broadcast("Hello team!");
    }
}
