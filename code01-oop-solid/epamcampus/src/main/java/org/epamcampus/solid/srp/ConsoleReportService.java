package org.epamcampus.solid.srp;

public class ConsoleReportService implements ReportService {

    @Override
    public void generateOrderReport(Order order) {
        System.out.println("  [ReportService] report_" + order.id() + ".txt → "
                + order.items().size() + " items, total=$" + order.calculateTotal());
    }
}
