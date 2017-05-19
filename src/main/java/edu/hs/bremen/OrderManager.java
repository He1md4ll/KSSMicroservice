package edu.hs.bremen;

import edu.hs.bremen.model.Order;
import edu.hs.bremen.model.User;
import edu.hs.bremen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderManager {
    private OrderRepository<Order, Long> orderRepository;

    @Autowired
    public OrderManager(OrderRepository<Order, Long> orderRepository) {
        this.orderRepository = orderRepository;
    }

    private Order createNewOrder(User user) {
        return new Order.OrderBuilder()
                .withUser(user)
                .withNewProductList()
                .build();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findOrder(User user) {
        return Optional.ofNullable(orderRepository.findByUser(user))
                .orElse(createNewOrder(user));
    }

    public Boolean deleteOrder(Order order) {
        orderRepository.delete(order);
        return Boolean.FALSE;
    }
}