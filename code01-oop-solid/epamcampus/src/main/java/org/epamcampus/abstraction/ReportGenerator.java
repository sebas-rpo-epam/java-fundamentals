package org.epamcampus.abstraction;

import java.time.Instant;
import java.util.Objects;

// Abstract class: has state (config), shared concrete behaviour, AND abstract steps
// sealed: we control who can extend this — no surprises from outside
public sealed abstract class ReportGenerator
        permits CsvReportGenerator, TextReportGenerator {

    // State: only possible in abstract class, NOT in interface
    protected final ReportConfiguration config;

    protected ReportGenerator(ReportConfiguration config) {
        this.config = Objects.requireNonNull(config);
    }

    // Template Method Pattern — skeleton is FIXED (final), steps are variable
    public final ReportOutput generate(ReportData data) {
        validateData(data);                         // concrete common step
        String header = buildHeader(data);          // abstract — subclass decides
        String body   = buildBody(data);            // abstract — subclass decides
        String footer = buildFooter();              // concrete common step
        return assembleReport(header, body, footer); // abstract — subclass decides
    }

    // Shared steps
    private void validateData(ReportData data) {
        if (data == null || data.rows().isEmpty()) {
            throw new IllegalArgumentException("Report data cannot be empty");
        }
    }

    private String buildFooter() {
        return "Generated at " + Instant.now() + " | Config: " + config.name()
                + " | Author: " + config.author();
    }

    // Variable steps — each format implements differently
    protected abstract String buildHeader(ReportData data);
    protected abstract String buildBody(ReportData data);
    protected abstract ReportOutput assembleReport(String header, String body, String footer);
}
