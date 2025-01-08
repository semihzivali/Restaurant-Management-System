package Controllers;

import Models.DataBaseConnection;
import application.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import Models.User;

public class WaiterTableOrderController {

    @FXML
    private Button back_button;
    @FXML
    private Button closeTableButton;
    @FXML
    private ListView<String> ordersListView;  // Oerders ListView
    @FXML
    private VBox menuBox;                    // VBox with menu items
    @FXML
    private Label totalPriceLabel;           // Total price

    private Map<String, Integer> orderQuantities = new HashMap<>(); // Map that holds product quantities

    private Connection connection;

    public WaiterTableOrderController() {
        connection = DataBaseConnection.getConnection();
    }

    @FXML
    public void initialize() {
        if (connection != null) {
            System.out.println("Veritabanına bağlantı başarılı!");
            loadMenu();                                                   // Loading Menu
            loadOrdersForTable(WaiterScreenController.tableNum); 
        } else {
            System.out.println("Veritabanına bağlantı kurulamadı!");
        }
    }

    // Load the menu
    private void loadMenu() {
        try {
            String query = "SELECT id, item_name, price FROM public.\"Menu\"";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String itemName = rs.getString("item_name");
                double itemPrice = rs.getDouble("price");

                HBox itemHBox = new HBox(10);

                Label itemLabel = new Label(itemName + " - " + itemPrice + " TL");
                Button minusButton = new Button("-");
                Label quantityLabel = new Label("0");
                Button plusButton = new Button("+");

                // Button functions
                minusButton.setOnAction(event -> updateQuantity(itemName, quantityLabel, -1));
                plusButton.setOnAction(event -> updateQuantity(itemName, quantityLabel, 1));

                // Add to HBox
                itemHBox.getChildren().addAll(itemLabel, minusButton, quantityLabel, plusButton);
                menuBox.getChildren().add(itemHBox);
            }

        } catch (SQLException e) {
            System.out.println("Menü yüklenirken hata oluştu: " + e.getMessage());
        }
    }

    @FXML
    private void loadOrdersForTable(int tableNumber) {
        try {
            String query = "SELECT items, quantities FROM public.\"Orders\" WHERE table_number = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, tableNumber);
            ResultSet rs = stmt.executeQuery();

            ordersListView.getItems().clear(); // Clear the previous data
            double totalPrice = 0.0; // Total price

            while (rs.next()) {
                String[] items = (String[]) rs.getArray("items").getArray();
                Integer[] quantities = (Integer[]) rs.getArray("quantities").getArray();

                for (int i = 0; i < items.length; i++) {
                    // Add orders to list
                    ordersListView.getItems().add(quantities[i] + " x " + items[i]);

                    // Get the product price and add it to the total price
                    double itemPrice = getItemPrice(items[i]);
                    totalPrice += quantities[i] * itemPrice;
                }
            }

            totalPriceLabel.setText(String.format("Total: %.2f TL", totalPrice));

        } catch (SQLException e) {
            System.out.println("Siparişleri yüklerken hata oluştu: " + e.getMessage());
        }
    }

    // Get product price from database
    private double getItemPrice(String itemName) {
        try {
            String query = "SELECT price FROM public.\"Menu\" WHERE item_name = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, itemName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            System.out.println("Ürün fiyatı alınırken hata oluştu: " + e.getMessage());
        }
        return 0.0;
    }

    // Update the quantity
    private void updateQuantity(String itemName, Label quantityLabel, int change) {
        int currentQuantity = orderQuantities.getOrDefault(itemName, 0);
        int newQuantity = currentQuantity + change;

        if (newQuantity >= 0) {
            orderQuantities.put(itemName, newQuantity);
            quantityLabel.setText(String.valueOf(newQuantity)); // Update the amount on the screen
            System.out.println(itemName + " quantity: " + newQuantity);
        }
    }

    @FXML
    private void handleDone() {
        if (orderQuantities.isEmpty()) {
            System.out.println("No order.");
            return;
        }

        try {
            int tableNumber = WaiterScreenController.tableNum;
            int waiterId = User.getWaiterId(); 
            String status = "Pending";

            String[] items = orderQuantities.keySet().toArray(new String[0]);
            Integer[] quantities = orderQuantities.values().toArray(new Integer[0]);

            String sql = "INSERT INTO public.\"Orders\" (table_number, status, waiter_id, quantities, items) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, tableNumber);
            stmt.setString(2, status);
            stmt.setInt(3, waiterId);
            stmt.setArray(4, connection.createArrayOf("integer", quantities));
            stmt.setArray(5, connection.createArrayOf("text", items));

            stmt.executeUpdate();
            
            String sql1 = "INSERT INTO public. m_orders (table_number, status, waiter_id, quantities, items) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt1 = connection.prepareStatement(sql1);

            stmt1.setInt(1, tableNumber);
            stmt1.setString(2, status);
            stmt1.setInt(3, waiterId);
            stmt1.setArray(4, connection.createArrayOf("integer", quantities));
            stmt1.setArray(5, connection.createArrayOf("text", items));

            stmt1.executeUpdate();
            System.out.println("Order saved successfully!");

            // Refresh the left panel
            refreshOrdersList(tableNumber);

            // Reset the orders.
            orderQuantities.clear();
            resetMenuQuantities();

        } catch (SQLException e) {
            System.out.println("An error occurred while adding an order.: " + e.getMessage());
        }
    }

    private void refreshOrdersList(int tableNumber) {
        loadOrdersForTable(tableNumber);
    }

    // Reset quantity labels in menu
    private void resetMenuQuantities() {
        for (javafx.scene.Node node : menuBox.getChildren()) {
            if (node instanceof HBox hBox) {
                for (javafx.scene.Node child : hBox.getChildren()) {
                    if (child instanceof Label label && isNumeric(label.getText())) {
                        label.setText("0"); 
                    }
                }
            }
        }
    }

    // Helper method that checks if the label is a quantity
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void closeTable() {
        try {
            int tableNumber = WaiterScreenController.tableNum;

            // Delete orders belonging to table from database
            String query = "DELETE FROM public.\"Orders\" WHERE table_number = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, tableNumber);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Table " + tableNumber + " closed successfully!");
            } else {
                System.out.println("No order found for this table.");
            }

            // Reset the left panel.
            ordersListView.getItems().clear();
            totalPriceLabel.setText("Total: 0.00 TL");

        } catch (SQLException e) {
            System.out.println("Error closing table: " + e.getMessage());
        }
    }

    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterScreen.fxml");
    }
}
