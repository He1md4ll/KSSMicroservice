package edu.hs.bremen.model.dto;

import com.google.common.collect.Lists;
import edu.hs.bremen.model.OrderEntity;

import java.util.List;

public class OrderDto {

    private CouponDto couponDto;
    private List<BasketEntryDto> basketEntryDtoList;

    public OrderDto(CouponDto couponDto, List<BasketEntryDto> basketEntryDtoList) {
        this.couponDto = couponDto;
        this.basketEntryDtoList = basketEntryDtoList;
    }

    public static OrderDto fromOrder(OrderEntity orderEntity) {
        final CouponDto couponDto = CouponDto.fromCouponEntity(orderEntity.getCouponEntity());
        final List<BasketEntryDto> basketEntryDtoList = Lists.transform(orderEntity.getBasketEntryEntityList(), BasketEntryDto::fromBasketEntry);
        return new OrderDto(couponDto, basketEntryDtoList);
    }

    public CouponDto getCouponDto() {
        return couponDto;
    }

    public List<BasketEntryDto> getBasketEntryDtoList() {
        return basketEntryDtoList;
    }
}