package org.epamcampus.df.xml.jaxb;

import jakarta.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;

public class JaxbExample {

    public static void run() {
        System.out.println("\n--- JAXB: Marshal / Unmarshal ---");
        try {
            // 1. Create an Employee object
            Employee emp = new Employee(42, "Diana Prince", "Security", new BigDecimal("120000.00"), "diana@epam.com");

            // 2. Marshal to XML string
            JAXBContext ctx = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(emp, sw);
            String xml = sw.toString();
            System.out.println("Marshalled XML:");
            System.out.println(xml);

            // 3. Unmarshal back to Employee
            Unmarshaller unmarshaller = ctx.createUnmarshaller();
            Employee roundTrip = (Employee) unmarshaller.unmarshal(new StringReader(xml));
            System.out.println("Round-trip result: " + roundTrip);

        } catch (JAXBException e) {
            throw new RuntimeException("JAXB example failed", e);
        }
    }
}
