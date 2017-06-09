package edu.hs.bremen.model.dto;

import com.google.common.collect.Lists;
import edu.hs.bremen.model.OrderEntity;

import java.util.List;

public class OrderDto {
    private List<ProductDto> productList;

    public OrderDto(List<ProductDto> productList) {
        this.productList = productList;
    }

    public static OrderDto fromOrder(OrderEntity orderEntity) {
        final List<ProductDto> productList = Lists.transform(orderEntity.getProductEntityList(), ProductDto::fromProduct);
        return new OrderDto(productList);
    }

    public List<ProductDto> getProductList() {
        return productList;
    }
}