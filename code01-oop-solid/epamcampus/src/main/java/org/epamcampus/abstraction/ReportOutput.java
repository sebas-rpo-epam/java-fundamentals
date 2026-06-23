package org.epamcampus.abstraction;

public record ReportOutput(String format, String content) {
    @Override
    public String toString() {
        return "[%s Report]\n%s".formatted(format, content);
    }
}
