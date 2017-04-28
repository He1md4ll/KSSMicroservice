package edu.hs.bremen.repository;


import edu.hs.bremen.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface OrderRepository<Order, ID extends Serializable> extends CrudRepository<Order, ID> {

    @Query("select o from Order o where o.user = ?1")
    Order findByUser(User user);
}