package edu.hs.bremen.repository;

import edu.hs.bremen.model.CouponEntity;
import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends CrudRepository<CouponEntity, Long> {
    CouponEntity findByCouponCode(String couponCode);
}