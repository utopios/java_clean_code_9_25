package org.example;

public class PayrollSystem {

    public double calculateSalary(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("L'employé ne peut pas être null");
        }
        return employee.calculateSalary();
    }

    public void printPayrollReport(Employee employee) {
        System.out.println("=== Fiche de Paie ===");
        System.out.println("Rôle: " + employee.getRole().getDisplayName());
        System.out.println(employee.getSalaryBreakdown());
        System.out.println("=====================");
    }

    public double calculateTotalPayroll(Employee... employees) {
        double total = 0;
        for (Employee employee : employees) {
            total += calculateSalary(employee);
        }
        return total;
    }
}