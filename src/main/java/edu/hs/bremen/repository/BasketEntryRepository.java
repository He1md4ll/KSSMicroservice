package edu.hs.bremen.repository;

import edu.hs.bremen.model.BasketEntryEntity;
import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface BasketEntryRepository extends CrudRepository<BasketEntryEntity, Long> {
    BasketEntryEntity findByProductEntityAndOrderEntity(ProductEntity productEntity, OrderEntity orderEntity);
}