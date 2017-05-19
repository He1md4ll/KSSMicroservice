package edu.hs.bremen.facade;

import edu.hs.bremen.OrderManager;
import edu.hs.bremen.ProductManager;
import edu.hs.bremen.model.Product;
import edu.hs.bremen.model.User;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultApiFacade implements IApiFacade {

    private ProductManager productManager;
    private OrderManager orderManager;

    @Autowired
    public DefaultApiFacade(ProductManager productManager, OrderManager orderManager) {
        this.productManager = productManager;
        this.orderManager = orderManager;
    }

    @Override
    public OrderDto linkProduct(String userId, ProductDto productDto) {
        final User user = new User.UserBuilder().withUuid(userId).build();
        final Product product = new Product.ProductBuilder()
                .withProductID(productDto.getProductId())
                .build();
        return OrderDto.fromOrder(productManager.addProductToOrder(user, product));
    }

    @Override
    public Boolean deleteProduct(String userId, ProductDto productDto) {
        // TODO: Add implementation
        return null;
    }

    @Override
    public OrderDto findOrder(String userId) {
        final User user = new User.UserBuilder().withUuid(userId).build();
        return OrderDto.fromOrder(orderManager.findOrder(user));
    }

    @Override
    public Boolean deleteOrder(String userId) {
        // TODO: Add implementation
        return null;
    }

    @Override
    public OrderDto createOrder(String userId) {
        // TODO: Add implementation
        return null;
    }
}