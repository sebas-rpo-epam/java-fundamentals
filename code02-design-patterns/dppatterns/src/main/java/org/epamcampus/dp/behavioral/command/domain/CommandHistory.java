package org.epamcampus.dp.behavioral.command.domain;

import org.epamcampus.dp.behavioral.command.pattern.Command;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CommandHistory {

    private final Deque<Command> history = new ArrayDeque<>();

    public void execute(Command command) {
        command.execute();
        history.push(command);
        System.out.println("  [History] Executed: " + command.describe());
    }

    public boolean undoLast() {
        if (history.isEmpty()) return false;
        Command c = history.pop();
        c.undo();
        System.out.println("  [History] Undone: " + c.describe());
        return true;
    }

    public void printHistory() {
        System.out.println("  [History] Remaining history (" + history.size() + " commands):");
        List<Command> list = new ArrayList<>(history);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + list.get(i).describe());
        }
    }
}
