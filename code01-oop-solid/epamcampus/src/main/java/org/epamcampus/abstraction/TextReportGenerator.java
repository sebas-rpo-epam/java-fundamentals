package org.epamcampus.abstraction;

public final class TextReportGenerator extends ReportGenerator {

    public TextReportGenerator(ReportConfiguration config) {
        super(config);
    }

    @Override
    protected String buildHeader(ReportData data) {
        String line = "=".repeat(40);
        return line + "\n  " + data.title().toUpperCase() + "\n" + line;
    }

    @Override
    protected String buildBody(ReportData data) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String row : data.rows()) {
            sb.append("  ").append(i++).append(". ").append(row).append("\n");
        }
        return sb.toString().stripTrailing();
    }

    @Override
    protected ReportOutput assembleReport(String header, String body, String footer) {
        return new ReportOutput("TEXT", header + "\n" + body + "\n" + "-".repeat(40) + "\n" + footer);
    }
}
