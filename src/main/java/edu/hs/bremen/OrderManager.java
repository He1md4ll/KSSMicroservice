package edu.hs.bremen;

import edu.hs.bremen.model.Order;
import edu.hs.bremen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class OrderManager {
    private OrderRepository<Order, Long> orderRepository;
    private Map<String, Order> orderMap;

    @Autowired
    public OrderManager(OrderRepository<Order, Long> orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findAllOrders(String userUID) {
        return null;
    }

    public Boolean deleteOrder(String userUID, Order order) {
        return Boolean.FALSE;
    }
}