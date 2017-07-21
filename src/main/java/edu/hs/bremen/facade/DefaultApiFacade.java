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

/**
 * Default Api facade implementation to wire manager behaviour together
 * Facade also responsible for entity - dto - conversion
 */
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

    /**
     * Adds basket entry to current order
     * If product already in basket then only count is updated
     * @param userUuid unique user id
     * @param basketEntryDto basket entry
     * @return updated order
     */
    @Override
    @Transactional
    public OrderDto linkBasketEntry(String userUuid, BasketEntryDto basketEntryDto) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        final BasketEntryEntity basketEntryEntity = basketManager.getUpdatedBasketEntry(userEntity, basketEntryDto, Boolean.FALSE);
        return OrderDto.fromOrder(basketManager.addBasketEntryToOrder(userEntity, basketEntryEntity));
    }

    /**
     * Subtracts count of product from corresponding basket entry
     * If count zero (or less) entry is removed from order
     * @param userUuid unique user id
     * @param basketEntryDto basket entry
     * @return updated order
     */
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

    /**
     * Gets current order of user.
     * If non is present then new one is created.
     * @param userUuid unique user id
     * @return order of user
     */
    @Override
    public OrderDto getOrder(String userUuid) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        return OrderDto.fromOrder(orderManager.getOrder(userEntity));
    }

    /**
     * Deletes current oder of user
     * @param userUuid unique user id
     */
    @Override
    @Transactional
    public void deleteOrder(String userUuid) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        orderManager.deleteOrder(orderEntity);
    }

    /**
     * Finds product using product id.
     * If product is not present either exception is thrown or product is created
     * - Mode can be switched in configuration (see application.properties)
     * @param productId unique id of product
     * @return product
     */
    @Override
    public ProductDto getProduct(String productId) {
        return ProductDto.fromProduct(productManager.getProduct(productId));
    }

    /**
     * Removes product with productID
     * @param productId unique id of product
     */
    @Override
    public void deleteProduct(String productId) {
        final ProductEntity productEntity = productManager.getProduct(productId);
        productManager.deleteProduct(productEntity);
    }

    /**
     * Checks coupon code.
     * If code is valid coupon is added to current order of user.
     * An already present coupon for an order will be replaced by the new one.
     * @param userUuid unique user id
     * @param couponCode unique coupon code
     * @return coupon
     */
    @Override
    public CouponDto verifyCoupon(String userUuid, String couponCode) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        return CouponDto.fromCouponEntity(couponManager.verifyCoupon(userEntity, couponCode));
    }

    /**
     * Deletes coupon from current order of user.
     * @param userUuid unique user id
     */
    @Override
    public void deleteCoupon(String userUuid) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        couponManager.deleteCoupon(userEntity);
    }
}