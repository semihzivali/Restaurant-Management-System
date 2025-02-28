package Controllers;

import Models.SalesReport;
import Services.Abstract.ISalesReportService;
import Utils.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManagerOrderDataController {

    @FXML
    private TableView<SalesReport> salesReportTable;

    @FXML
    private TableColumn<SalesReport, String> waiterNameColumn;

    @FXML
    private TableColumn<SalesReport, Double> totalSalesColumn;

    @FXML
    private Button backButton;

    @FXML
    private Label totalSalesLabel; 

    private ISalesReportService salesReportService;
    public ManagerOrderDataController(ISalesReportService _salesReportService) {
    	this.salesReportService = _salesReportService;
    }
    
    private ObservableList<SalesReport> salesReportData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Fill the table with data
        waiterNameColumn.setCellValueFactory(cellData -> cellData.getValue().getWaiterNameProperty());
        totalSalesColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalSalesProperty().asObject());

        loadSalesReportData();
            
    }

    private void loadSalesReportData() {
        salesReportData.clear();
        salesReportData.addAll(salesReportService.getSalesReportData());
        salesReportTable.setItems(salesReportData);

        // Calculate total sales of all waiters
        double totalSales = salesReportService.getTotalSales();
        totalSalesLabel.setText("Total Sales: " + totalSales);
    }
    
    @FXML
    public void Back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
    }
}
