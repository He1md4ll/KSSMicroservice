package edu.hs.bremen.manager;

import edu.hs.bremen.model.CouponEntity;
import edu.hs.bremen.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class CouponManager {

    private CouponRepository couponRepository;

    @Autowired
    public CouponManager(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public CouponEntity verifyCoupon(String couponCode) {
        return couponRepository.findByCouponCode(couponCode);
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