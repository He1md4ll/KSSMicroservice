package edu.hs.bremen.manager;

import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.UserEntity;
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

    private OrderEntity createNewOrder(UserEntity user) {
        final OrderEntity orderEntity = new OrderEntity.OrderBuilder()
                .withUser(user)
                .withNewBasketEntryList()
                .build();
        orderRepository.save(orderEntity);
        return orderEntity;
    }

    public void saveOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    public OrderEntity getOrder(final UserEntity userEntity) {
        return Optional.ofNullable(orderRepository.findFirstByUserEntity(userEntity))
                .orElseGet(() -> createNewOrder(userEntity));
    }

    public void deleteOrder(OrderEntity orderEntity) {
        orderRepository.delete(orderEntity);
    }
}