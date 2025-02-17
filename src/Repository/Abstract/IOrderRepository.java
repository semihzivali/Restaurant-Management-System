package Repository.Abstract;

import java.util.List;

import Models.Order;

public interface IOrderRepository {
	
	public void createOrder(Order order);
	
	public List<Order> getOrdersByTable(int tableNumber);
	 
	public void updateOrderStatus(int orderId, String status);
	
	public void deleteOrderByTable(int tableNumber);
}
