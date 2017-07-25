package edu.hs.bremen.manager;

import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.UserEntity;
import edu.hs.bremen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Manager to handle all order related operations
 * including data manipulation and creation.
 */
@Service
public class OrderManager {

    private OrderRepository orderRepository;

    @Autowired
    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Creates and saves new order for passed user with empty basket list.
     * @param user user
     * @return new order for user
     */
    private OrderEntity createNewOrder(UserEntity user) {
        final OrderEntity orderEntity = new OrderEntity.OrderBuilder()
                .withUser(user)
                .withNewBasketEntryList()
                .build();
        orderRepository.save(orderEntity);
        return orderEntity;
    }

    /**
     * Saves passed order in database.
     * @param orderEntity order
     */
    public void saveOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    /**
     * Gets current order of user. If non is present in database new one is created.
     * @param userEntity current user
     * @return order of user
     */
    public OrderEntity getOrder(final UserEntity userEntity) {
        return Optional.ofNullable(orderRepository.findFirstByUserEntity(userEntity))
                .orElseGet(() -> createNewOrder(userEntity));
    }

    /**
     * Deletes current order of user which will result in creation of a new empty order.
     * @param orderEntity order to delete
     */
    public void deleteOrder(OrderEntity orderEntity) {
        orderRepository.delete(orderEntity);
    }
}