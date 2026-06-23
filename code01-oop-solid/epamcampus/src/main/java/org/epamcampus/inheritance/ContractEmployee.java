package org.epamcampus.inheritance;

import java.math.BigDecimal;
import java.util.Objects;

public final class ContractEmployee extends Employee {

    private final BigDecimal hourlyRate;
    private final int hoursWorkedThisMonth;

    public ContractEmployee(String id, String name, String dept,
                            BigDecimal hourlyRate, int hoursWorked) {
        super(id, name, dept);
        this.hourlyRate           = Objects.requireNonNull(hourlyRate);
        this.hoursWorkedThisMonth = hoursWorked;
    }

    @Override
    public BigDecimal calculateMonthlySalary() {
        return hourlyRate.multiply(new BigDecimal(hoursWorkedThisMonth));
    }
}
