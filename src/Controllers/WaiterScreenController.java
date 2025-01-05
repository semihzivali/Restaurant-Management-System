package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Services.OrderService;
import Models.Order;
import Utils.AlertHelper;

public class WaiterScreenController {

    private OrderService orderService = new OrderService();

    @FXML
    private Button table1Button;
    @FXML
    private Button table2Button;
    @FXML
    private Button table3Button;
    @FXML
    private Button table4Button;
    @FXML
    private Button table5Button;
    @FXML
    private Button table6Button;
    @FXML
    private TextField orderItemsField;

    private int selectedTable = -1;

    @FXML
    public void initialize() {
        // Başlangıçta bir masa seçilmemiştir.
        selectedTable = -1;
    }

    @FXML
    public void selectTable1(ActionEvent event) {
        selectedTable = 1;
    }

    @FXML
    public void selectTable2(ActionEvent event) {
        selectedTable = 2;
    }

    @FXML
    public void selectTable3(ActionEvent event) {
        selectedTable = 3;
    }

    @FXML
    public void selectTable4(ActionEvent event) {
        selectedTable = 4;
    }

    @FXML
    public void selectTable5(ActionEvent event) {
        selectedTable = 5;
    }

    @FXML
    public void selectTable6(ActionEvent event) {
        selectedTable = 6;
    }

    @FXML
    public void submitOrder(ActionEvent event) {
        if (selectedTable == -1) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Masa Seçilmedi", "Lütfen bir masa seçin.");
            return;
        }

        String orderItems = orderItemsField.getText();
        if (orderItems.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Sipariş Boş", "Sipariş kısmını doldurun.");
            return;
        }

        // Yeni sipariş oluşturuluyor
        //Order newOrder = new Order(selectedTable, orderItems, "pending", 1); // 1, garson id'si varsayılan olarak
        orderService.createOrder(selectedTable, orderItems, "pending", 1);

        AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Sipariş Verildi", "Sipariş başarıyla verildi.");

        // Siparişi verdikten sonra orderItemsField temizleniyor ve masa sıfırlanıyor
        orderItemsField.clear();
        selectedTable = -1;
    }
}