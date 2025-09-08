package org.example.peche;

public class ReportService {
    private final MySQLRepository repo = new MySQLRepository();
    private final PdfExporter expo = new PdfExporter();

    void generateMonthly() {
        String[] data = repo.fetch();
        expo.toPdf(data);
    }
}
