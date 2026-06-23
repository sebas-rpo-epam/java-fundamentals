package org.epamcampus.df.xml.jaxb;

import jakarta.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private String department;
    @XmlElement
    private BigDecimal salary;
    @XmlElement
    private String email;

    // no-arg constructor required by JAXB
    public Employee() {}

    public Employee(int id, String name, String department, BigDecimal salary, String email) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public BigDecimal getSalary() { return salary; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Employee[id=" + id + ", name=" + name + ", dept=" + department + ", salary=" + salary + "]";
    }
}
