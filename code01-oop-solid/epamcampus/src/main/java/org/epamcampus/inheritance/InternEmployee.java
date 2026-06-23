package org.epamcampus.inheritance;

import java.math.BigDecimal;

public final class InternEmployee extends Employee {

    private static final BigDecimal MONTHLY_STIPEND = new BigDecimal("800.00");

    public InternEmployee(String id, String name, String dept) {
        super(id, name, dept);
    }

    @Override
    public BigDecimal calculateMonthlySalary() {
        return MONTHLY_STIPEND;
    }
}
