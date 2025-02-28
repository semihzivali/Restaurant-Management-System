package Repository.Concrete;

import Models.SalesReport;
import Repository.Abstract.ISalesReportRepository;
import Models.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalesReportRepository implements ISalesReportRepository{

    // Method that brings sales reports
    public List<SalesReport> getSalesReportData() {
        List<SalesReport> salesReports = new ArrayList<>();
        try (Connection conn = DataBaseConnection.getConnection()) {
            String query = "SELECT u.username AS waiter_name, SUM(m.price * q.quantity) AS total_sales " +
                           "FROM public.m_orders o " +
                           "JOIN public.\"Users\" u ON o.waiter_id = u.id " +
                           "JOIN public.\"Menu\" m ON m.item_name = ANY(o.items) " +
                           "JOIN UNNEST(o.items, o.quantities) AS q(item_name, quantity) ON m.item_name = q.item_name " +
                           "GROUP BY u.username";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                salesReports.add(new SalesReport(
                        rs.getString("waiter_name"),
                        rs.getDouble("total_sales")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesReports;
    }

    // Method to calculate total sales of all waiters
    public double getTotalSales() {
        double totalSales = 0;
        try (Connection conn = DataBaseConnection.getConnection()) {
            String query = "SELECT SUM(m.price * q.quantity) AS total_sales " +
                           "FROM public.m_orders o " +
                           "JOIN public.\"Menu\" m ON m.item_name = ANY(o.items) " +
                           "JOIN UNNEST(o.items, o.quantities) AS q(item_name, quantity) ON m.item_name = q.item_name";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalSales = rs.getDouble("total_sales");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalSales;
    }
}
