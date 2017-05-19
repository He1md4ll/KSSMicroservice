package edu.hs.bremen.facade;

import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;

public interface IApiFacade {
    OrderDto linkProduct(String userId, ProductDto productDto);
    Boolean deleteProduct(String userId, ProductDto productDto);
    OrderDto findOrder(String userId);
    Boolean deleteOrder(String userId);
    OrderDto createOrder(String userId);
}