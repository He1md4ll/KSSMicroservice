package edu.hs.bremen.repository;

import edu.hs.bremen.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByProductID(String productID);
}