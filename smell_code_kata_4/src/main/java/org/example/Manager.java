package org.example;


public class Manager extends Employee {
    private final int teamSize;

    public Manager(int hoursWorked, int teamSize) {
        super(hoursWorked, EmployeeRole.MANAGER);
        if (teamSize < 0) {
            throw new IllegalArgumentException("La taille de l'équipe ne peut pas être négative");
        }
        this.teamSize = teamSize;
    }

    @Override
    public double calculateSalary() {
        double baseSalary = calculateBaseSalary(PayrollConfiguration.MANAGER_HOURLY_RATE);
        return baseSalary + calculateTeamSizeBonus();
    }

    @Override
    public String getSalaryBreakdown() {
        double baseSalary = calculateBaseSalary(PayrollConfiguration.MANAGER_HOURLY_RATE);
        double bonus = calculateTeamSizeBonus();

        StringBuilder breakdown = new StringBuilder();
        breakdown.append(String.format("Salaire de base: %.2f€ (%d heures × %.2f€)%n",
                baseSalary, hoursWorked, PayrollConfiguration.MANAGER_HOURLY_RATE));

        if (bonus > 0) {
            breakdown.append(String.format("Bonus équipe: %.2f€ (> %d personnes dans l'équipe)%n",
                    bonus, PayrollConfiguration.MANAGER_TEAM_SIZE_THRESHOLD));
        }

        breakdown.append(String.format("Total: %.2f€", calculateSalary()));
        return breakdown.toString();
    }

    private double calculateTeamSizeBonus() {
        return teamSize > PayrollConfiguration.MANAGER_TEAM_SIZE_THRESHOLD ?
                PayrollConfiguration.MANAGER_BONUS : 0;
    }

    public int getTeamSize() {
        return teamSize;
    }
}
