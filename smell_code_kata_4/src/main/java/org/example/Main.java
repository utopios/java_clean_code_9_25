package org.example;

public class Main {
    public static void main(String[] args) {
        PayrollSystem payroll = new PayrollSystem();

        Employee seniorDev = new Developer(160, 8);
        Employee juniorDev = new Developer(150, 2);
        Employee bigTeamManager = new Manager(140, 15);
        Employee smallTeamManager = new Manager(150, 5);
        Employee intern = new Intern(120);

        payroll.printPayrollReport(seniorDev);
        payroll.printPayrollReport(bigTeamManager);
        payroll.printPayrollReport(intern);

        double totalPayroll = payroll.calculateTotalPayroll(
                seniorDev, juniorDev, bigTeamManager, smallTeamManager, intern
        );

        System.out.println("Total des salaires: " + String.format("%.2f€", totalPayroll));
    }
}