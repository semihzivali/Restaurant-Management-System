package Controllers;

import Models.Menu;
import Services.MenuService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

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
    private TableColumn<Menu, Void> actionColumn;

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

    private final MenuService menuService = new MenuService();
    private ObservableList<Menu> menuData = FXCollections.observableArrayList();

    private Menu selectedMenuForEdit; // Edit işleminde düzenlenen öğeyi takip eder

    @FXML
    public void initialize() {
        // Sütunların bağlanması
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        stockQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

        // Verilerin yüklenmesi
        loadMenuData();

        // Action sütununun eklenmesi
        addActionButtons();

        // Add butonuna tıklama işlemi
        addButton.setOnAction(event -> {
            if (selectedMenuForEdit == null) {
                addMenu();
            } else {
                updateMenu();
            }
        });
    }

    private void loadMenuData() {
        menuData.clear();
        menuData.addAll(menuService.getAllMenuItems());
        menuTable.setItems(menuData);
    }

    private void addActionButtons() {
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.setOnAction(event -> {
                    Menu selectedItem = getTableView().getItems().get(getIndex());
                    populateFieldsForEdit(selectedItem);
                });

                deleteButton.setOnAction(event -> {
                    Menu selectedItem = getTableView().getItems().get(getIndex());
                    deleteMenu(selectedItem);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox actionBox = new HBox(10, editButton, deleteButton);
                    setGraphic(actionBox);
                }
            }
        });
    }

    private void populateFieldsForEdit(Menu menu) {
        selectedMenuForEdit = menu; // Düzenlenecek öğeyi takip et
        itemNameField.setText(menu.getItemName());
        priceField.setText(String.valueOf(menu.getPrice()));
        categoryIdField.setText(String.valueOf(menu.getCategoryId()));
        stockQuantityField.setText(String.valueOf(menu.getStockQuantity()));
        addButton.setText("Update"); // Buton metnini değiştir
    }

    private void addMenu() {
        Menu menu = new Menu(
        		
                itemNameField.getText(),
                Double.parseDouble(priceField.getText()),
                Integer.parseInt(categoryIdField.getText()),
                Integer.parseInt(stockQuantityField.getText())
        );
        menuService.addMenuItem(menu);
        clearFields();
        loadMenuData();
    }

    private void updateMenu() {
        selectedMenuForEdit.setItemName(itemNameField.getText());
        selectedMenuForEdit.setPrice(Double.parseDouble(priceField.getText()));
        selectedMenuForEdit.setCategoryId(Integer.parseInt(categoryIdField.getText()));
        selectedMenuForEdit.setStockQuantity(Integer.parseInt(stockQuantityField.getText()));

        menuService.updateMenuItem(selectedMenuForEdit); // Veritabanında güncelle
        clearFields();
        loadMenuData();
        addButton.setText("Add"); // Butonu eski haline döndür
        selectedMenuForEdit = null; // Edit işlemi tamamlandı
    }

    private void deleteMenu(Menu menu) {
        menuService.deleteMenuItem(menu.getId());
        loadMenuData();
    }

    private void clearFields() {
        itemNameField.clear();
        priceField.clear();
        categoryIdField.clear();
        stockQuantityField.clear();
    }
}
