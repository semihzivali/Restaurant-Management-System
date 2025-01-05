package Controllers;

import Models.Menu;
import Services.MenuService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ManagerMenuController {

    @FXML
    private TableView<Menu> menuTable;

    @FXML
    private TableColumn<Menu, Integer> idColumn;

    @FXML
    private TableColumn<Menu, String> itemNameColumn;

    @FXML
    private TableColumn<Menu, Double> priceColumn;

    @FXML
    private TableColumn<Menu, Integer> categoryColumn;

    @FXML
    private TableColumn<Menu, Integer> stockQuantityColumn;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField categoryIdField;

    @FXML
    private TextField stockQuantityField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private final MenuService menuService = new MenuService(); // Service katmanına erişim
    private ObservableList<Menu> menuData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
      //  idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        //itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        //priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        //categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryIdProperty().asObject());
        //stockQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().stockQuantityProperty().asObject());

        loadMenuData();

        addButton.setOnAction(event -> addMenu());
        updateButton.setOnAction(event -> updateMenu());
        deleteButton.setOnAction(event -> deleteMenu());
    }

    private void loadMenuData() {
        menuData.clear();
        menuData.addAll(menuService.getAllMenuItems());
        menuTable.setItems(menuData);
    }

    private void addMenu() {
        Menu Menu = new Menu(
                0, // ID veritabanı tarafından atanır
                itemNameField.getText(),
                Double.parseDouble(priceField.getText()),
                Integer.parseInt(categoryIdField.getText()),
                Integer.parseInt(stockQuantityField.getText())
        );
        menuService.addMenuItem(Menu);
        loadMenuData();
    }

    private void updateMenu() {
        Menu selectedItem = menuTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedItem.setItemName(itemNameField.getText());
            selectedItem.setPrice(Double.parseDouble(priceField.getText()));
            selectedItem.setCategoryId(Integer.parseInt(categoryIdField.getText()));
            selectedItem.setStockQuantity(Integer.parseInt(stockQuantityField.getText()));

            menuService.updateMenuItem(selectedItem);
            loadMenuData();
        }
    }

    private void deleteMenu() {
        Menu selectedItem = menuTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            menuService.deleteMenuItem(selectedItem.getId());
            loadMenuData();
        }
    }
}