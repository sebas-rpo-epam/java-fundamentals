package org.epamcampus.df.xml.demo;

import org.epamcampus.df.xml.dom.DomParserExample;
import org.epamcampus.df.xml.stax.StaxParserExample;
import org.epamcampus.df.xml.jaxb.JaxbExample;
import org.epamcampus.df.xml.bad.BadXmlParser;
import org.epamcampus.df.xml.security.SafeXmlParser;

public class MainXml {
    public static void run() {
        System.out.println("\n" + "─".repeat(50));
        System.out.println("  XML en Java");
        System.out.println("─".repeat(50));

        DomParserExample.run();
        StaxParserExample.run();
        JaxbExample.run();
        BadXmlParser.demonstrateRisk();
        SafeXmlParser.demonstrateSecurity();
    }
}
