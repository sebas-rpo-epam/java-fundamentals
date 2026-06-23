package org.epamcampus.dp.behavioral.command.pattern;

public interface Command {
    void execute();
    void undo();
    String describe();
}
