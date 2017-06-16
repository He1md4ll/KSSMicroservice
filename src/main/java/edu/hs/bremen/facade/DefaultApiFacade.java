package edu.hs.bremen.facade;

import edu.hs.bremen.manager.OrderManager;
import edu.hs.bremen.manager.ProductManager;
import edu.hs.bremen.manager.UserManager;
import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.ProductEntity;
import edu.hs.bremen.model.UserEntity;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;
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
    public OrderDto linkProduct(String userUuid, ProductDto productDto) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        final ProductEntity productEntity = productManager.getUpdatedProduct(productDto, Boolean.FALSE);
        return OrderDto.fromOrder(productManager.addProductToOrder(userEntity, productEntity));
    }

    @Override
    @Transactional
    public void deleteProduct(String userUuid, ProductDto productDto) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        final ProductEntity productEntity = productManager.getUpdatedProduct(productDto, Boolean.TRUE);
        if (productEntity.getProductCount() <= 0) {
            productManager.deleteProductFromOrder(userEntity, productEntity);
        }
    }

    @Override
    public OrderDto getOrder(String userUuid) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        return OrderDto.fromOrder(orderManager.getOrder(userEntity));
    }

    @Override
    @Transactional
    public void deleteOrder(String userUuid) {
        final UserEntity userEntity = userManager.getUser(userUuid);
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        orderManager.deleteOrder(orderEntity);
    }
}