package org.epamcampus.inheritance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class FullTimeEmployee extends Employee {

    private final BigDecimal annualSalary;

    public FullTimeEmployee(String id, String name, String dept, BigDecimal annualSalary) {
        super(id, name, dept);
        this.annualSalary = Objects.requireNonNull(annualSalary);
    }

    @Override
    public BigDecimal calculateMonthlySalary() {
        return annualSalary.divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP);
    }
}
