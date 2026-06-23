package org.epamcampus.df;

import org.epamcampus.df.xml.demo.MainXml;
import org.epamcampus.df.json.demo.MainJson;
import org.epamcampus.df.jackson.demo.MainJackson;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  EPAM Campus — Module 03: Data Formats");
        System.out.println("=".repeat(60));

        MainXml.run();
        MainJson.run();
        MainJackson.run();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("  All demos completed.");
        System.out.println("=".repeat(60));
    }
}
