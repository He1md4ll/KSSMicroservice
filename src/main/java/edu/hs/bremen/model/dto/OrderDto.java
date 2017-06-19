package edu.hs.bremen.model.dto;

import com.google.common.collect.Lists;
import edu.hs.bremen.model.OrderEntity;

import java.util.List;

public class OrderDto {

    private List<BasketEntryDto> basketEntryDtoList;

    public OrderDto(List<BasketEntryDto> basketEntryDtoList) {
        this.basketEntryDtoList = basketEntryDtoList;
    }

    public static OrderDto fromOrder(OrderEntity orderEntity) {
        final List<BasketEntryDto> basketEntryDtoList = Lists.transform(orderEntity.getBasketEntryEntityList(), BasketEntryDto::fromBasketEntry);
        return new OrderDto(basketEntryDtoList);
    }

    public List<BasketEntryDto> getBasketEntryDtoList() {
        return basketEntryDtoList;
    }
}