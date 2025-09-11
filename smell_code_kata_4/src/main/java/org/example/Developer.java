package org.example;


public class Developer extends Employee {
    private final int yearsOfExperience;

    public Developer(int hoursWorked, int yearsOfExperience) {
        super(hoursWorked, EmployeeRole.DEVELOPER);
        if (yearsOfExperience < 0) {
            throw new IllegalArgumentException("L'expérience ne peut pas être négative");
        }
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public double calculateSalary() {
        double baseSalary = calculateBaseSalary(PayrollConfiguration.DEVELOPER_HOURLY_RATE);
        return baseSalary + calculateExperienceBonus();
    }

    @Override
    public String getSalaryBreakdown() {
        double baseSalary = calculateBaseSalary(PayrollConfiguration.DEVELOPER_HOURLY_RATE);
        double bonus = calculateExperienceBonus();

        StringBuilder breakdown = new StringBuilder();
        breakdown.append(String.format("Salaire de base: %.2f€ (%d heures × %.2f€)%n",
                baseSalary, hoursWorked, PayrollConfiguration.DEVELOPER_HOURLY_RATE));

        if (bonus > 0) {
            breakdown.append(String.format("Bonus expérience: %.2f€ (> %d ans d'expérience)%n",
                    bonus, PayrollConfiguration.DEVELOPER_EXPERIENCE_THRESHOLD));
        }

        breakdown.append(String.format("Total: %.2f€", calculateSalary()));
        return breakdown.toString();
    }

    private double calculateExperienceBonus() {
        return yearsOfExperience > PayrollConfiguration.DEVELOPER_EXPERIENCE_THRESHOLD ?
                PayrollConfiguration.DEVELOPER_BONUS : 0;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
}