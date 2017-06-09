package edu.hs.bremen.repository;


import edu.hs.bremen.model.Order;
import edu.hs.bremen.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("select o from Order o where o.user = ?1")
    Order findByUser(User user);
}