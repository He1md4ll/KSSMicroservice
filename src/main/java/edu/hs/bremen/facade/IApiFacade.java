package edu.hs.bremen.facade;

import edu.hs.bremen.model.dto.BasketEntryDto;
import edu.hs.bremen.model.dto.CouponDto;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;

public interface IApiFacade {
    OrderDto linkBasketEntry(String userUuid, BasketEntryDto basketEntryDto);
    OrderDto deleteBasketEntry(String userUuid, BasketEntryDto basketEntryDto);
    OrderDto getOrder(String userUuid);
    void deleteOrder(String userUuid);
    ProductDto getProduct(String productId);
    void deleteProduct(String productId);
    CouponDto verifyCoupon(String couponCode);
}