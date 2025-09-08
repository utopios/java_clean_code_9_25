package org.example.couplage;

public class ReportGenerator {
    //private DatabaseConnection dbConnection = new DatabaseConnection();
    private final Database dbConnection;

    public ReportGenerator(Database dbConnection) {
        this.dbConnection = dbConnection;
    }
}
