package org.epamcampus.df.xml.stax;

import javax.xml.stream.*;
import java.io.InputStream;

public class StaxParserExample {

    public static void run() {
        System.out.println("\n--- StAX Parser ---");
        System.out.println("StAX: pull-based streaming — low memory, cursor-controlled.");

        try (InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("employees.xml")) {

            if (is == null) {
                System.out.println("employees.xml not found on classpath.");
                return;
            }

            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(is);

            String currentElement = null;
            String currentId = null;
            String name = null;
            String department = null;

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    currentElement = reader.getLocalName();
                    if ("employee".equals(currentElement)) {
                        currentId = reader.getAttributeValue(null, "id");
                        name = null;
                        department = null;
                    }
                } else if (event == XMLStreamConstants.CHARACTERS) {
                    if ("name".equals(currentElement)) {
                        name = reader.getText().trim();
                    } else if ("department".equals(currentElement)) {
                        department = reader.getText().trim();
                    }
                } else if (event == XMLStreamConstants.END_ELEMENT) {
                    if ("employee".equals(reader.getLocalName())) {
                        System.out.println("  id=" + currentId + ", name=" + name + ", dept=" + department);
                    }
                    currentElement = null;
                }
            }
            reader.close();

        } catch (Exception e) {
            throw new RuntimeException("StAX parsing failed", e);
        }
    }
}
