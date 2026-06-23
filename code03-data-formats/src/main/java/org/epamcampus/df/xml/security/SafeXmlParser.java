package org.epamcampus.df.xml.security;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

public class SafeXmlParser {

    // Factory method that creates an XXE-safe DocumentBuilder
    public static DocumentBuilder createSafeBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Disable DOCTYPE declarations entirely
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        // Disable external general entities
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        // Disable external parameter entities
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        // Disable external DTD
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);
        return factory.newDocumentBuilder();
    }

    public static void demonstrateSecurity() {
        System.out.println("\n--- XXE Security: Safe XML Parser ---");
        System.out.println("Safe parser configured with:");
        System.out.println("  - disallow-doctype-decl: true");
        System.out.println("  - external-general-entities: false");
        System.out.println("  - external-parameter-entities: false");
        System.out.println("  - load-external-dtd: false");
        System.out.println("This parser REJECTS malicious XML like:");
        System.out.println("  <!DOCTYPE foo [<!ENTITY xxe SYSTEM \"file:///etc/passwd\">]>");
        System.out.println("  <document>&xxe;</document>");
        System.out.println("Result: XXE attack prevented.");
    }
}
