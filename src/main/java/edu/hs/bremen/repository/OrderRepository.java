package edu.hs.bremen.repository;


import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
    OrderEntity findFirstByUserEntity(UserEntity userEntity);
}