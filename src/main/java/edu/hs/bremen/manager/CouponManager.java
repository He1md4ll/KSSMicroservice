package edu.hs.bremen.manager;

import edu.hs.bremen.model.CouponEntity;
import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.UserEntity;
import edu.hs.bremen.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class CouponManager {

    private CouponRepository couponRepository;
    private OrderManager orderManager;

    @Autowired
    public CouponManager(CouponRepository couponRepository, OrderManager orderManager) {
        this.couponRepository = couponRepository;
        this.orderManager = orderManager;
    }

    public CouponEntity verifyCoupon(UserEntity userEntity, String couponCode) {
        final CouponEntity couponEntity = couponRepository.findByCouponCode(couponCode);
        if (couponEntity != null ) {
            final OrderEntity orderEntity = orderManager.getOrder(userEntity);
            orderEntity.setCouponEntity(couponEntity);
            orderManager.saveOrder(orderEntity);
            return couponEntity;
        }
        return null;
    }

    @PostConstruct
    private void init(){
        CouponEntity couponEntity = new CouponEntity.CouponBuilder()
                .withCouponCode("noMoney")
                .withRate(50)
                .withValidFrom(new Date())
                .build();
        couponRepository.save(couponEntity);
    }
}