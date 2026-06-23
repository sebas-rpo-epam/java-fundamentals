package org.epamcampus.inheritance;

import java.math.BigDecimal;
import java.util.Objects;

// sealed: only the listed classes can extend this — compiler enforces the hierarchy
public sealed abstract class Employee
        permits FullTimeEmployee, ContractEmployee, InternEmployee {

    private final String employeeId;
    private final String name;
    private final String department;

    protected Employee(String employeeId, String name, String department) {
        this.employeeId = Objects.requireNonNull(employeeId, "employeeId cannot be null");
        this.name       = Objects.requireNonNull(name, "name cannot be null");
        this.department = Objects.requireNonNull(department, "department cannot be null");
    }

    // Contract: every subclass MUST calculate salary differently
    public abstract BigDecimal calculateMonthlySalary();

    // Concrete behaviour shared by all employees
    public String generatePayslipHeader() {
        return "Payslip for %s (%s) — Dept: %s".formatted(name, employeeId, department);
    }

    // Template Method Pattern: skeleton is fixed (final), variable steps are abstract
    public final String generateFullPayslip() {
        BigDecimal gross = calculateMonthlySalary();
        BigDecimal net   = gross.multiply(new BigDecimal("0.78"));
        return """
                %s
                  Gross Salary : $%s
                  Net Salary   : $%s  (22%% tax withheld)
                """.formatted(generatePayslipHeader(), gross, net);
    }

    public String getEmployeeId() { return employeeId; }
    public String getName()       { return name; }
    public String getDepartment() { return department; }
}
