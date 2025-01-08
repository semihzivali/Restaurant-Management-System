package Models;

public class Order {

    private int tableNumber;
    private String items;
    private String status;
    private int waiterId;

    public Order( int tableNumber, String items, String status, int waiterId) {
        this.tableNumber = tableNumber;
        this.items = items;
        this.status = status;
        this.waiterId = waiterId;
    }

    // Getters and Setters
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }
}