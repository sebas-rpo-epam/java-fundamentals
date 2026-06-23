package org.epamcampus;

import org.epamcampus.encapsulation.MainEncapsulation;
import org.epamcampus.inheritance.MainInheritance;
import org.epamcampus.polymorphism.MainPolymorphism;
import org.epamcampus.abstraction.MainAbstraction;
import org.epamcampus.solid.srp.MainSrp;
import org.epamcampus.solid.ocp.MainOcp;
import org.epamcampus.solid.lsp.MainLsp;
import org.epamcampus.solid.isp.MainIsp;
import org.epamcampus.solid.dip.MainDip;
import org.epamcampus.composition.MainComposition;
import org.epamcampus.ioc.MainIoc;

public class Main {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   EPAM Campus — Java Fundamentals                    ║");
        System.out.println("║   Module 01: OOP & Design Principles                 ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        // ── OOP Pillars ────────────────────────────────────────────────────
        MainEncapsulation.run();
        MainInheritance.run();
        MainPolymorphism.run();
        MainAbstraction.run();

        // ── SOLID Principles ───────────────────────────────────────────────
        MainSrp.run();
        MainOcp.run();
        MainLsp.run();
        MainIsp.run();
        MainDip.run();

        // ── Composition & IoC ──────────────────────────────────────────────
        MainComposition.run();
        MainIoc.run();

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║   All demos complete.                                ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
