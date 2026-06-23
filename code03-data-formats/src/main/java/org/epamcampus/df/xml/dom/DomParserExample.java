package org.epamcampus.df.xml.dom;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;

public class DomParserExample {

    public static void run() {
        System.out.println("\n--- DOM Parser ---");
        System.out.println("DOM loads the entire document into memory — avoid for large files.");

        try (InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("employees.xml")) {

            if (is == null) {
                System.out.println("employees.xml not found on classpath.");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList employees = doc.getElementsByTagName("employee");
            System.out.println("Employees found: " + employees.getLength());

            for (int i = 0; i < employees.getLength(); i++) {
                Element emp = (Element) employees.item(i);
                String id = emp.getAttribute("id");
                String name = emp.getElementsByTagName("name").item(0).getTextContent();
                String department = emp.getElementsByTagName("department").item(0).getTextContent();
                System.out.println("  id=" + id + ", name=" + name + ", dept=" + department);
            }

        } catch (Exception e) {
            throw new RuntimeException("DOM parsing failed", e);
        }
    }
}
