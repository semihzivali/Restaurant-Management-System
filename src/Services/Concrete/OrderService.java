package Services.Concrete;

import Models.Order;
import Repository.Abstract.IOrderRepository;
import Services.Abstract.IOrderService;

import java.util.List;

public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;
    
    public OrderService(IOrderRepository orderRepository) {
    	this.orderRepository = orderRepository;
    }
  
    @Override
    public void createOrder(int tableNumber, String[] items, String status, int waiterId, int[] quantities) {
    	if(tableNumber>0) {
    		Order order = new Order(tableNumber, items, quantities, "pending", waiterId);
    		orderRepository.createOrder(order);
    	}
    	else {
             throw new IllegalArgumentException("Invalid table number: " + tableNumber);
        }
    }
   
    @Override
    public List<Order> getOrdersByTable(int tableNumber) {
    	if(tableNumber>0) {
    		return orderRepository.getOrdersByTable(tableNumber);
    	}
    	else {
            throw new IllegalArgumentException("Invalid table number: " + tableNumber);
       }
    }
   
    @Override
    public void updateOrderStatus(int orderId, String status) {
        orderRepository.updateOrderStatus(orderId, status);
    }
    
    @Override
    public void deleteOrderByTable(int tableNumber) {
        orderRepository.deleteOrderByTable(tableNumber);
    }
}