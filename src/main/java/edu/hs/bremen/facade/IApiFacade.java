package edu.hs.bremen.facade;

import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;

public interface IApiFacade {
    OrderDto linkProduct(String userUuid, ProductDto productDto);
    void deleteProduct(String userUuid, ProductDto productDto);
    OrderDto getOrder(String userUuid);
    void deleteOrder(String userUuid);
}