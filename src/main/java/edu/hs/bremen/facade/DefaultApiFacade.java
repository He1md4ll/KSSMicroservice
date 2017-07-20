package edu.hs.bremen.facade;

import edu.hs.bremen.manager.*;
import edu.hs.bremen.model.BasketEntryEntity;
import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.ProductEntity;
import edu.hs.bremen.model.UserEntity;
import edu.hs.bremen.model.dto.BasketEntryDto;
import edu.hs.bremen.model.dto.CouponDto;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DefaultApiFacade implements IApiFacade {

    private BasketManager basketManager;
    private ProductManager productManager;
    private OrderManager orderManager;
    private UserManager userManager;
    private CouponManager couponManager;

    @Autowired
    public DefaultApiFacade(BasketManager basketManager, ProductManager productManager, OrderManager orderManager, UserManager userManager, CouponManager couponManager) {
        this.basketManager = basketManager;
        this.productManager = productManager;
        this.orderManager = orderManager;
        this.userManager = userManager;
        this.couponManager = couponManager;
    }

    @Override
    @Transactional
    public OrderDto linkBasketEntry(String userUuid, BasketEntryDto basketEntryDto) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        final BasketEntryEntity basketEntryEntity = basketManager.getUpdatedBasketEntry(userEntity, basketEntryDto, Boolean.FALSE);
        return OrderDto.fromOrder(basketManager.addBasketEntryToOrder(userEntity, basketEntryEntity));
    }

    @Override
    @Transactional
    public OrderDto deleteBasketEntry(String userUuid, BasketEntryDto basketEntryDto) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        final BasketEntryEntity basketEntryEntity = basketManager.getUpdatedBasketEntry(userEntity, basketEntryDto, Boolean.TRUE);
        if (basketEntryEntity.getProductCount() <= 0) {
            basketManager.deleteBasketEntryFromOrder(userEntity, basketEntryEntity);
        }
        return OrderDto.fromOrder(orderManager.getOrder(userEntity));
    }

    @Override
    public OrderDto getOrder(String userUuid) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        return OrderDto.fromOrder(orderManager.getOrder(userEntity));
    }

    @Override
    @Transactional
    public void deleteOrder(String userUuid) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        orderManager.deleteOrder(orderEntity);
    }

    @Override
    public ProductDto getProduct(String productId) {
        return ProductDto.fromProduct(productManager.getProduct(productId));
    }

    @Override
    public void deleteProduct(String productId) {
        final ProductEntity productEntity = productManager.getProduct(productId);
        productManager.deleteProduct(productEntity);
    }

    @Override
    public CouponDto verifyCoupon(String userUuid, String couponCode) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        return CouponDto.fromCouponEntity(couponManager.verifyCoupon(userEntity, couponCode));
    }

    @Override
    public void deleteCoupon(String userUuid) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        couponManager.deleteCoupon(userEntity);
    }
}