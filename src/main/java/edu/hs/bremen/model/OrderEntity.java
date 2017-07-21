package edu.hs.bremen.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CouponEntity couponEntity;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BasketEntryEntity> basketEntryEntityList;

    public List<BasketEntryEntity> getBasketEntryEntityList() {
        return basketEntryEntityList;
    }

    private void setBasketEntryEntityList(List<BasketEntryEntity> basketEntryEntityList) {
        this.basketEntryEntityList = basketEntryEntityList;
    }

    public void addBasketEntry(BasketEntryEntity basketEntryEntity) {
        basketEntryEntityList.add(basketEntryEntity);
    }

    public Boolean deleteBasketEntry(BasketEntryEntity basketEntryEntity) {
        return basketEntryEntityList.remove(basketEntryEntity);
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    private void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CouponEntity getCouponEntity() {
        return couponEntity;
    }

    public void setCouponEntity(CouponEntity couponEntity) {
        this.couponEntity = couponEntity;
    }

    public static class OrderBuilder {
        private OrderEntity orderEntity = new OrderEntity();

        public OrderBuilder withUser(UserEntity userEntity) {
            orderEntity.setUserEntity(userEntity);
            return this;
        }

        public OrderBuilder withCoupon(CouponEntity couponEntity) {
            orderEntity.setCouponEntity(couponEntity);
            return this;
        }

        public OrderBuilder withBasketEntryList(List<BasketEntryEntity> basketEntryEntityList) {
            orderEntity.setBasketEntryEntityList(basketEntryEntityList);
            return this;
        }

        public OrderBuilder withNewBasketEntryList() {
            orderEntity.setBasketEntryEntityList(Lists.newArrayList());
            return this;
        }

        public OrderEntity build() {
            return orderEntity;
        }
    }
}
