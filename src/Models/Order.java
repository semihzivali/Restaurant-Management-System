package Models;

public class Order {
    private int tableNumber;
    private String[] items; 
    private int[] quantities; 
    private String status;
    private int waiterId;

    // Constructor
    public Order(int tableNumber, String[] items, int[] quantities, String status, int waiterId) {
        this.tableNumber = tableNumber;
        this.items = items;
        this.quantities = quantities;
        this.status = status;
        this.waiterId = waiterId;
    }

    // Getters and setters (if needed)
    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public int[] getQuantities() {
        return quantities;
    }

    public void setQuantities(int[] quantities) {
        this.quantities = quantities;
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
