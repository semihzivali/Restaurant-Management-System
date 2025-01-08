package Repository;

import Models.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Models.DataBaseConnection;

public class OrderRepository {

    public void addOrder(Order order) {
        String query = "INSERT INTO \"Orders\" (table_number, items, status, waiter_id) VALUES (?, ?, ?, ?)";

        try (Connection con = DataBaseConnection.getConnection();  // DataBaseConnection
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, order.getTableNumber());
            pst.setString(2, order.getItems());
            pst.setString(3, order.getStatus());
            pst.setInt(4, order.getWaiterId());

            pst.executeUpdate();
            System.out.println("Order added successfully.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Getting orders
    public List<Order> getOrdersByTable(int tableNumber) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM \"Orders\" WHERE table_number = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, tableNumber);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("table_number"),
                    rs.getString("items"),
                    rs.getString("status"),
                    rs.getInt("waiter_id")
                );
                orders.add(order);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }

    public void updateOrderStatus(int orderId, String status) {
        String query = "UPDATE \"Orders\" SET status = ? WHERE id = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, status);
            pst.setInt(2, orderId);
            pst.executeUpdate();

            System.out.println("Order status updated.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}