package edu.hs.bremen.repository;


import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface OrderRepository<Order, ID extends Serializable> extends CrudRepository<Order, ID> {
}