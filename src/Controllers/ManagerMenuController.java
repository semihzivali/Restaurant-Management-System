package Controllers;

import Models.Menu;
import Services.Abstract.IMenuService;
import Utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    @FXML
    private Button back_button;
    
    private IMenuService menuService;
    public ManagerMenuController(IMenuService _menuService) {
    	menuService = _menuService;
    }
    
    private ObservableList<Menu> menuData = FXCollections.observableArrayList();

    private Menu selectedMenuForEdit; // Keeps track of the edited item during the edit process

    @FXML
    public void initialize() {
        // Connecting columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        stockQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("stockQuantity"));

        loadMenuData();

        addActionButtons();

        // Clicking on the Add button
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
        selectedMenuForEdit = menu; // Follow the item to be edited
        itemNameField.setText(menu.getItemName());
        priceField.setText(String.valueOf(menu.getPrice()));
        categoryIdField.setText(String.valueOf(menu.getCategoryId()));
        stockQuantityField.setText(String.valueOf(menu.getStockQuantity()));
        addButton.setText("Update"); // Change button text
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

        menuService.updateMenuItem(selectedMenuForEdit); // Update in database
        clearFields();
        loadMenuData();
        addButton.setText("Add");
        selectedMenuForEdit = null; // Editing process completed
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
    
    public void back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
    }
}
