package edu.hs.bremen.facade;

import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;
import edu.hs.bremen.model.dto.UserDto;

public interface IApiFacade {
    OrderDto linkProduct(UserDto userDto, ProductDto productDto);
    void deleteProduct(UserDto userDto, ProductDto productDto);
    OrderDto getOrder(UserDto userDto);
    void deleteOrder(UserDto userDto);
}