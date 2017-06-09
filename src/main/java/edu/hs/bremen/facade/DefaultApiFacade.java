package edu.hs.bremen.facade;

import edu.hs.bremen.manager.OrderManager;
import edu.hs.bremen.manager.ProductManager;
import edu.hs.bremen.manager.UserManager;
import edu.hs.bremen.model.Order;
import edu.hs.bremen.model.Product;
import edu.hs.bremen.model.User;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;
import edu.hs.bremen.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DefaultApiFacade implements IApiFacade {

    private ProductManager productManager;
    private OrderManager orderManager;
    private UserManager userManager;

    @Autowired
    public DefaultApiFacade(ProductManager productManager, OrderManager orderManager, UserManager userManager) {
        this.productManager = productManager;
        this.orderManager = orderManager;
        this.userManager = userManager;
    }

    @Override
    @Transactional
    public OrderDto linkProduct(UserDto userDto, ProductDto productDto) {
        final User user = userManager.getUser(userDto);
        final Product product = productManager.getProduct(productDto);
        return OrderDto.fromOrder(productManager.addProductToOrder(user, product));
    }

    @Override
    @Transactional
    public void deleteProduct(UserDto userDto, ProductDto productDto) {
        final User user = userManager.getUser(userDto);
        final Product product = productManager.getProduct(productDto);
        productManager.deleteProductFromOrder(user, product);
    }

    @Override
    public OrderDto getOrder(UserDto userDto) {
        final User user = userManager.getUser(userDto);
        return OrderDto.fromOrder(orderManager.getOrder(user));
    }

    @Override
    @Transactional
    public void deleteOrder(UserDto userDto) {
        final User user = userManager.getUser(userDto);
        final Order order = orderManager.getOrder(user);
        orderManager.deleteOrder(order);
    }
}