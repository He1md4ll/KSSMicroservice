package edu.hs.bremen.manager;

import edu.hs.bremen.model.Order;
import edu.hs.bremen.model.User;
import edu.hs.bremen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderManager {

    private OrderRepository orderRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private Order createNewOrder(User user) {
        final Order order = new Order.OrderBuilder()
                .withUser(user)
                .withNewProductList()
                .build();
        orderRepository.save(order);
        return order;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrder(User user) {
        return Optional.ofNullable(orderRepository.findByUser(user))
                .orElse(createNewOrder(user));
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}