package Services.Abstract;

import java.util.List;
import Models.Order;

public interface IOrderService {
	
	public void createOrder(int tableNumber, String[] items, String status, int waiterId, int[] quantities);
	
	public List<Order> getOrdersByTable(int tableNumber);
	 
	public void updateOrderStatus(int orderId, String status);
	
	public void deleteOrderByTable(int tableNumber);

}
