package org.example;


public abstract class Employee {
    protected final int hoursWorked;
    protected final EmployeeRole role;

    protected Employee(int hoursWorked, EmployeeRole role) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Les heures travaillées ne peuvent pas être négatives");
        }
        this.hoursWorked = hoursWorked;
        this.role = role;
    }

    public abstract double calculateSalary();

    public abstract String getSalaryBreakdown();

    public int getHoursWorked() {
        return hoursWorked;
    }

    public EmployeeRole getRole() {
        return role;
    }

    protected double calculateBaseSalary(double hourlyRate) {
        return hoursWorked * hourlyRate;
    }
}
