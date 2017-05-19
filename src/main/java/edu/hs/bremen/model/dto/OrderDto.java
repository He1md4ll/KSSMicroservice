package edu.hs.bremen.model.dto;

import com.google.common.collect.Lists;
import edu.hs.bremen.model.Order;

import java.util.List;

public class OrderDto {
    private List<ProductDto> productList;

    public OrderDto(List<ProductDto> productList) {
        this.productList = productList;
    }

    public static OrderDto fromOrder(Order order) {
        final List<ProductDto> productList = Lists.transform(order.getProductList(), ProductDto::fromProduct);
        return new OrderDto(productList);
    }

    public List<ProductDto> getProductList() {
        return productList;
    }
}