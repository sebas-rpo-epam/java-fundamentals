package org.epamcampus.abstraction;

public final class CsvReportGenerator extends ReportGenerator {

    public CsvReportGenerator(ReportConfiguration config) {
        super(config);
    }

    @Override
    protected String buildHeader(ReportData data) {
        return "\"Report\",\"" + data.title() + "\"";
    }

    @Override
    protected String buildBody(ReportData data) {
        return String.join("\n", data.rows().stream()
                .map(row -> "\"" + row + "\"")
                .toList());
    }

    @Override
    protected ReportOutput assembleReport(String header, String body, String footer) {
        return new ReportOutput("CSV", header + "\n" + body + "\n\"" + footer + "\"");
    }
}
