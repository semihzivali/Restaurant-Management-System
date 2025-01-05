package Services;

import Models.Order;
import Repository.OrderRepository;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();

    // Sipariş ekleme
    public void createOrder(int tableNumber, String items, String status, int waiterId) {
        Order order = new Order(tableNumber, items, "pending", waiterId); // id 0 çünkü yeni sipariş
        orderRepository.addOrder(order);
    }

    // Siparişleri al
    public List<Order> getOrdersByTable(int tableNumber) {
        return orderRepository.getOrdersByTable(tableNumber);
    }

    // Sipariş durumu güncelle
    public void updateOrderStatus(int orderId, String status) {
        orderRepository.updateOrderStatus(orderId, status);
    }
}