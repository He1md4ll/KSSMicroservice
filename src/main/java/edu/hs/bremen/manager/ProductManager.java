package edu.hs.bremen.manager;

import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.ProductEntity;
import edu.hs.bremen.model.UserEntity;
import edu.hs.bremen.model.dto.ProductDto;
import edu.hs.bremen.repository.ProductRepository;
import edu.hs.bremen.validation.exception.ProductNotLinkedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManager {

    private OrderManager orderManager;
    private ProductRepository productRepository;

    @Autowired
    public ProductManager(OrderManager orderManager, ProductRepository productRepository) {
        this.orderManager = orderManager;
        this.productRepository = productRepository;
    }

    public ProductEntity getProduct(final ProductDto productDto) {
        return Optional.ofNullable(productRepository
                .findByProductID(productDto.getProductId()))
                .orElseGet(() -> createNewProduct(productDto));
    }

    public OrderEntity addProductToOrder(UserEntity userEntity, ProductEntity productEntity) {
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        orderEntity.addProduct(productEntity);
        orderManager.saveOrder(orderEntity);
        return orderEntity;
    }

    public void deleteProductFromOrder(UserEntity userEntity, ProductEntity productEntity) {
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        Boolean deleteted = orderEntity.deleteProduct(productEntity);
        if (deleteted) {
            orderManager.saveOrder(orderEntity);
            cleanProduct(productEntity.getProductID());
        } else {
            throw new ProductNotLinkedException();
        }
    }

    public List<ProductEntity> getProductsForOrder(UserEntity userEntity) {
        return orderManager.getOrder(userEntity).getProductEntityList();
    }

    private void cleanProduct(final String productID) {
        final ProductEntity productEntity = productRepository.findByProductID(productID);
        if (productEntity.getOrderEntityList().isEmpty()) {
            productRepository.delete(productEntity);
        }
    }

    private ProductEntity createNewProduct(final ProductDto productDto) {
        final ProductEntity productEntity = new ProductEntity.ProductBuilder()
                .withProductID(productDto.getProductId())
                .withCount(productDto.getCount())
                .build();
        productRepository.save(productEntity);
        return productEntity;
    }
}