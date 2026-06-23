package org.epamcampus.dp.behavioral.mediator.demo;

import org.epamcampus.dp.behavioral.mediator.bad.BadDirectChat;
import org.epamcampus.dp.behavioral.mediator.impl.ChatRoom;
import org.epamcampus.dp.behavioral.mediator.impl.ChatUser;

public class MainMediator {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║         PATTERN 9: MEDIATOR              ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println("\n--- BAD: O(N²) direct coupling ---");
        new BadDirectChat().demonstrate();
        System.out.println("  (Adding Dave requires wiring him to ALL existing users manually)");

        System.out.println("\n--- GOOD: ChatRoom mediates all communication ---");

        ChatRoom chatRoom = new ChatRoom();

        ChatUser alice = new ChatUser("Alice", chatRoom);
        ChatUser bob   = new ChatUser("Bob",   chatRoom);
        ChatUser carol = new ChatUser("Carol", chatRoom);
        ChatUser dave  = new ChatUser("Dave",  chatRoom);

        chatRoom.register(alice);
        chatRoom.register(bob);
        chatRoom.register(carol);
        chatRoom.register(dave);

        System.out.println("\n  [Alice broadcasts]");
        alice.sendMessage("Good morning team!");

        System.out.println("\n  [Bob sends private to Alice]");
        bob.sendMessageTo("Alice", "Can you review my PR?");

        System.out.println("\n  [Carol broadcasts]");
        carol.sendMessage("PR link: github.com/epam-campus/review/42");

        System.out.println("\n  [Adding Eve — zero changes to existing users]");
        ChatUser eve = new ChatUser("Eve", chatRoom);
        chatRoom.register(eve); // ✅ just register with the mediator

        System.out.println("\n  [Eve broadcasts]");
        eve.sendMessage("Hello everyone! Happy to be here.");

        System.out.println("\nKey insight: Adding Eve required zero changes to Alice, Bob, Carol, or Dave." +
                " Mediator (ChatRoom) handles all routing — users only know the mediator.");
        // Spring MVC: DispatcherServlet routes HTTP requests — controllers never call each other
        // CQRS: CommandBus routes commands to handlers — same Mediator pattern
    }
}
