package Controllers;

import Models.DataBaseConnection;
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

public class WaiterTableOrderController {

    @FXML
    private ListView<String> ordersListView;  // Sipariş ListView
    @FXML
    private VBox menuBox;                    // Menü ürünlerinin olduğu VBox

    private Map<String, Integer> orderQuantities = new HashMap<>(); // Ürün miktarlarını tutan harita

    private Connection connection;

    public WaiterTableOrderController() {
        connection = DataBaseConnection.getConnection();
    }

    @FXML
    public void initialize() {
        if (connection != null) {
            System.out.println("Veritabanına bağlantı başarılı!");
            loadMenu();
        } else {
            System.out.println("Veritabanına bağlantı kurulamadı!");
        }
    }

    // Menüdeki ürünleri yükle
    private void loadMenu() {
        try {
            String query = "SELECT id, item_name, price FROM public.\"Menu\"";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String itemName = rs.getString("item_name");
                double itemPrice = rs.getDouble("price");

                // Menüdeki her ürün için HBox oluştur
                HBox itemHBox = new HBox(10);

                Label itemLabel = new Label(itemName + " - " + itemPrice + " TL");
                Button minusButton = new Button("-");
                Label quantityLabel = new Label("0");
                Button plusButton = new Button("+");

                // Buton işlevleri
                minusButton.setOnAction(event -> updateQuantity(itemName, quantityLabel, -1));
                plusButton.setOnAction(event -> updateQuantity(itemName, quantityLabel, 1));

                // HBox içine ekle
                itemHBox.getChildren().addAll(itemLabel, minusButton, quantityLabel, plusButton);
                menuBox.getChildren().add(itemHBox);
            }

        } catch (SQLException e) {
            System.out.println("Menü yüklenirken hata oluştu: " + e.getMessage());
        }
    }

    // Miktar güncelleme işlemi
    private void updateQuantity(String itemName, Label quantityLabel, int change) {
        int currentQuantity = orderQuantities.getOrDefault(itemName, 0);
        int newQuantity = currentQuantity + change;

        if (newQuantity >= 0) {
            orderQuantities.put(itemName, newQuantity);
            quantityLabel.setText(String.valueOf(newQuantity)); // Ekrandaki miktarı güncelle
            System.out.println(itemName + " miktarı: " + newQuantity); // Konsola yazdır
        }
    }

    @FXML
    private void handleDone() {
        if (orderQuantities.isEmpty()) {
            System.out.println("Sipariş bulunmuyor.");
            return;
        }

        try {
            // Veritabanına bağlan
            Connection connection = DataBaseConnection.getConnection();
            
            // Sipariş bilgilerini hazırlayın
            int tableNumber = WaiterScreenController.tableNum; // Örnek olarak masa numarasını sabit verdim, UI'dan alınabilir
            int waiterId = 1; // Örnek olarak garson ID'si sabit verdim, UI'dan alınabilir
            String status = "Ready"; // Yeni sipariş durumu

            // `items` ve `quantities` dizilerini hazırlayın
            String[] items = orderQuantities.keySet().toArray(new String[0]);
            Integer[] quantities = orderQuantities.values().toArray(new Integer[0]);

            // SQL INSERT komutunu hazırlayın
            String sql = "INSERT INTO public.\"Orders\" (table_number, status, waiter_id, quantities, items) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, tableNumber);
            stmt.setString(2, status);
            stmt.setInt(3, waiterId);
            stmt.setArray(4, connection.createArrayOf("integer", quantities));
            stmt.setArray(5, connection.createArrayOf("text", items));

            stmt.executeUpdate();
            System.out.println("Sipariş başarıyla kaydedildi!");

            // Sol tarafta toplam siparişleri göster
            ordersListView.getItems().add("Masa: " + tableNumber + " - Durum: " + status);

            // Siparişleri sıfırla
            orderQuantities.clear();
            resetMenuQuantities();

        } catch (SQLException e) {
            System.out.println("Sipariş eklerken hata oluştu: " + e.getMessage());
        }
    }

    // Menüdeki miktar etiketlerini sıfırla
    private void resetMenuQuantities() {
        for (javafx.scene.Node node : menuBox.getChildren()) {
            if (node instanceof HBox hBox) {
                for (javafx.scene.Node child : hBox.getChildren()) {
                    if (child instanceof Label label && isNumeric(label.getText())) {
                        label.setText("0"); // Miktar etiketini sıfırla
                    }
                }
            }
        }
    }

    // Label'ın miktar olduğunu kontrol eden yardımcı metot
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
