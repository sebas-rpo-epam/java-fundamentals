package org.epamcampus.dp;

import org.epamcampus.dp.creational.builder.demo.MainBuilder;
import org.epamcampus.dp.creational.abstractfactory.demo.MainAbstractFactory;
import org.epamcampus.dp.structural.adapter.demo.MainAdapter;
import org.epamcampus.dp.structural.composite.demo.MainComposite;
import org.epamcampus.dp.structural.decorator.demo.MainDecorator;
import org.epamcampus.dp.behavioral.command.demo.MainCommand;
import org.epamcampus.dp.behavioral.strategy.demo.MainStrategy;
import org.epamcampus.dp.behavioral.observer.demo.MainObserver;
import org.epamcampus.dp.behavioral.mediator.demo.MainMediator;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   EPAM Campus — Java Fundamentals                        ║");
        System.out.println("║   Module 02: Design Patterns                             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();

        MainBuilder.run();
        MainAbstractFactory.run();
        MainAdapter.run();
        MainComposite.run();
        MainDecorator.run();
        MainCommand.run();
        MainStrategy.run();
        MainObserver.run();
        MainMediator.run();
    }
}
