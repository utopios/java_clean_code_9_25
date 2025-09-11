package org.example;


public class Intern extends Employee {

    public Intern(int hoursWorked) {
        super(hoursWorked, EmployeeRole.INTERN);
    }

    @Override
    public double calculateSalary() {
        return calculateBaseSalary(PayrollConfiguration.INTERN_HOURLY_RATE);
    }

    @Override
    public String getSalaryBreakdown() {
        double salary = calculateSalary();
        return String.format("Salaire stagiaire: %.2f€ (%d heures × %.2f€)",
                salary, hoursWorked, PayrollConfiguration.INTERN_HOURLY_RATE);
    }
}
