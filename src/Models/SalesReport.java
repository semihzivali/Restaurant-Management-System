package Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesReport {

    private final StringProperty waiterName;
    private final DoubleProperty totalSales;

    public SalesReport(String waiterName, double totalSales) {
        this.waiterName = new SimpleStringProperty(waiterName);
        this.totalSales = new SimpleDoubleProperty(totalSales);
    }

    public String getWaiterName() {
        return waiterName.get();
    }

    public StringProperty getWaiterNameProperty() {
        return waiterName;
    }

    public double getTotalSales() {
        return totalSales.get();
    }

    public DoubleProperty getTotalSalesProperty() {
        return totalSales;
    }
}
