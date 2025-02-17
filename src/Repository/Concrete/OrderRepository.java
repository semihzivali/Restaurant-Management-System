package Repository.Concrete;

import Models.Order;
import Repository.Abstract.IOrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.DataBaseConnection;

public class OrderRepository implements IOrderRepository{

	public void createOrder(Order order) {
	    String query = "INSERT INTO \"Orders\" (table_number, items, status, waiter_id, quantities) VALUES (?, ?, ?, ?, ?)";

	    try (Connection con = DataBaseConnection.getConnection();  
	         PreparedStatement pst = con.prepareStatement(query)) {

	        // Set table number, status, and waiter ID
	        pst.setInt(1, order.getTableNumber());
	        pst.setString(2, String.join(",", order.getItems()));  // Join items into a single string
	        pst.setString(3, order.getStatus());
	        pst.setInt(4, order.getWaiterId());
	        
	        // Convert quantities to a PostgreSQL array format (e.g., {1,2,3})
	        StringBuilder quantitiesBuilder = new StringBuilder("{");
	        for (int i = 0; i < order.getQuantities().length; i++) {
	            quantitiesBuilder.append(order.getQuantities()[i]);
	            if (i < order.getQuantities().length - 1) {
	                quantitiesBuilder.append(",");
	            }
	        }
	        quantitiesBuilder.append("}");

	        // Set the quantities as a PostgreSQL array
	        pst.setString(5, quantitiesBuilder.toString());

	        // Execute the query
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
	            // Get the 'items' and 'quantities' as arrays from the ResultSet
	            String[] items = (String[]) rs.getArray("items").getArray();
	            int[] quantities = (int[]) rs.getArray("quantities").getArray();

	            // Assuming the Order constructor takes items, quantities, status, tableNumber, and waiterId
	            Order order = new Order(
	                rs.getInt("table_number"),
	                items,  // items array
	                quantities,  // quantities array
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
    
    public void deleteOrderByTable(int tableNumber) {
        String query = "DELETE FROM \"Orders\" WHERE table_number = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, tableNumber);
            pst.executeUpdate();
            System.out.println("Orders for table " + tableNumber + " deleted successfully.");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}