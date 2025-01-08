package Controllers;

import Models.SalesReport;
import Services.SalesReportService;
import application.SceneManager;
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
    private Label totalSalesLabel; // Toplam satışları göstermek için Label

    private final SalesReportService salesReportService = new SalesReportService();
    private ObservableList<SalesReport> salesReportData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Tabloyu verilerle doldur
        waiterNameColumn.setCellValueFactory(cellData -> cellData.getValue().getWaiterNameProperty());
        totalSalesColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalSalesProperty().asObject());

        loadSalesReportData();
            
    }

    private void loadSalesReportData() {
        salesReportData.clear();
        salesReportData.addAll(salesReportService.getSalesReportData());
        salesReportTable.setItems(salesReportData);

        // Tüm garsonların toplam satışını hesapla
        double totalSales = salesReportService.getTotalSales(); // Bu metodu SalesReportService'de yazıyoruz
        totalSalesLabel.setText("Total Sales: " + totalSales);
    }
    
    @FXML
    public void Back(ActionEvent event) {
        SceneManager.getInstance().changeScene("/Views/ManagerScreen.fxml");
    }
}
