package Controllers;

import Models.User;
import Services.Abstract.IMenuService;
import Services.Abstract.IOrderService;
import Utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class WaiterTableOrderController {

    @FXML
    private Button back_button;
    @FXML
    private Button closeTableButton;
    @FXML
    private ListView<String> ordersListView;
    @FXML
    private VBox menuBox;
    @FXML
    private Label totalPriceLabel;

    private Map<String, Integer> orderQuantities = new HashMap<>();

    private IOrderService orderService;
    private IMenuService menuService;

    public WaiterTableOrderController(IOrderService _orderService, IMenuService _menuRepository) {
        this.orderService = _orderService;
        this.menuService = _menuRepository;
    }

    @FXML
    public void initialize() {
        loadMenu();
        loadOrdersForTable(WaiterScreenController.tableNum);
    }

    private void loadMenu() {
        var menuItems = menuService.getAllMenuItems();

        for (var item : menuItems) {
            HBox itemHBox = new HBox(10);

            Label itemLabel = new Label(item.getItemName() + " - " + item.getPrice() + " TL");
            Button minusButton = new Button("-");
            Label quantityLabel = new Label("0");
            Button plusButton = new Button("+");

            minusButton.setOnAction(event -> updateQuantity(item.getItemName(), quantityLabel, -1));
            plusButton.setOnAction(event -> updateQuantity(item.getItemName(), quantityLabel, 1));

            itemHBox.getChildren().addAll(itemLabel, minusButton, quantityLabel, plusButton);
            menuBox.getChildren().add(itemHBox);
        }
    }

    private void loadOrdersForTable(int tableNumber) {
        var orders = orderService.getOrdersByTable(tableNumber);
        ordersListView.getItems().clear();

        double totalPrice = 0.0;

        for (var order : orders) {
            String[] items = order.getItems();
            int[] quantities = order.getQuantities();

            for (int i = 0; i < items.length; i++) {
                var menuItem = menuService.getMenuItemById(Integer.parseInt(items[i]));
                if (menuItem != null) {
                    ordersListView.getItems().add(quantities[i] + " x " + menuItem.getItemName());
                    totalPrice += quantities[i] * menuItem.getPrice();
                }
            }
        }
        
        totalPriceLabel.setText(String.format("Total: %.2f TL", totalPrice));
    }

    private void updateQuantity(String itemName, Label quantityLabel, int change) {
        int currentQuantity = orderQuantities.getOrDefault(itemName, 0);
        int newQuantity = Math.max(0, currentQuantity + change);
        
        orderQuantities.put(itemName, newQuantity);
        quantityLabel.setText(String.valueOf(newQuantity));
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
            int[] quantities = orderQuantities.values().stream().mapToInt(Integer::intValue).toArray();

            orderService.createOrder(tableNumber, items, status, waiterId, quantities);
            
            System.out.println("Order saved successfully!");
            refreshOrdersList(tableNumber);
            orderQuantities.clear();
            resetMenuQuantities();

        } catch (Exception e) {
            System.out.println("An error occurred while adding an order: " + e.getMessage());
        }
    }

    @FXML
    private void closeTable() {
        try {
            int tableNumber = WaiterScreenController.tableNum;
            orderService.deleteOrderByTable(tableNumber);

            ordersListView.getItems().clear();
            totalPriceLabel.setText("Total: 0.00 TL");

            System.out.println("Table " + tableNumber + " closed successfully!");

        } catch (Exception e) {
            System.out.println("Error closing table: " + e.getMessage());
        }
    }

    private void refreshOrdersList(int tableNumber) {
        loadOrdersForTable(tableNumber);
    }

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

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/WaiterScreen.fxml");
    }
}
