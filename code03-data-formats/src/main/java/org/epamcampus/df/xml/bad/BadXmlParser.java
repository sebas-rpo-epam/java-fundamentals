package org.epamcampus.df.xml.bad;

import javax.xml.parsers.DocumentBuilderFactory;

// ANTI-PATTERN: Default DocumentBuilderFactory without XXE protection.
// An attacker can craft XML with external entity references to read server files.
//
// Example malicious XML:
//   <!DOCTYPE foo [<!ENTITY xxe SYSTEM "file:///etc/passwd">]>
//   <document>&xxe;</document>
//
// This parser would happily process it, exposing /etc/passwd content.
// This is OWASP Top 10 — XML External Entities (XXE) injection.
public class BadXmlParser {

    public static DocumentBuilderFactory createUnsafeFactory() {
        // No security configuration — vulnerable to XXE
        return DocumentBuilderFactory.newInstance();
    }

    public static void demonstrateRisk() {
        System.out.println("\n--- BAD: Unsafe XML Parser (XXE Vulnerable) ---");
        System.out.println("DocumentBuilderFactory.newInstance() with NO security config.");
        System.out.println("Vulnerable to XXE injection — can expose server files.");
        System.out.println("See SafeXmlParser for the correct approach.");
    }
}
