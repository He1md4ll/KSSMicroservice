package edu.hs.bremen.repository;

import edu.hs.bremen.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    ProductEntity findByProductID(String productID);
}