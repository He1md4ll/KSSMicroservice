package edu.hs.bremen.manager;

import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.ProductEntity;
import edu.hs.bremen.model.UserEntity;
import edu.hs.bremen.model.dto.ProductDto;
import edu.hs.bremen.repository.ProductRepository;
import edu.hs.bremen.validation.exception.ProductNotLinkedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductManager.class.getSimpleName());
    private OrderManager orderManager;
    private ProductRepository productRepository;

    @Autowired
    public ProductManager(OrderManager orderManager, ProductRepository productRepository) {
        this.orderManager = orderManager;
        this.productRepository = productRepository;
    }

    public ProductEntity getUpdatedProduct(final ProductDto productDto, boolean delete) {
        Optional<ProductEntity> productEntityOptional = Optional.ofNullable(productRepository
                .findByProductID(productDto.getProductId()));

        if (productEntityOptional.isPresent()) {
            updateProduct(productEntityOptional.get(), productDto, delete);
        } else {
            productEntityOptional = Optional.of(createNewProduct(productDto));
        }
        return productEntityOptional.get();
    }

    public OrderEntity addProductToOrder(UserEntity userEntity, ProductEntity productEntity) {
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        if (orderEntity.getProductEntityList().contains(productEntity)) {
            LOGGER.debug("Product {} already part of order.", productEntity.getProductID());
        } else {
            orderEntity.addProduct(productEntity);
            orderManager.saveOrder(orderEntity);
            LOGGER.info("Product {} added to order.", productEntity.getProductID());
        }
        return orderEntity;
    }

    public void deleteProductFromOrder(UserEntity userEntity, ProductEntity productEntity) {
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        Boolean deleteted = orderEntity.deleteProduct(productEntity);
        if (deleteted) {
            orderManager.saveOrder(orderEntity);
            cleanProduct(productEntity.getProductID());
            LOGGER.info("Product {} deleted from order.", productEntity.getProductID());
        } else {
            throw new ProductNotLinkedException();
        }
    }

    private void cleanProduct(final String productID) {
        final ProductEntity productEntity = productRepository.findByProductID(productID);
        if (productEntity.getOrderEntityList().isEmpty()) {
            productRepository.delete(productEntity);
            LOGGER.info("Product {} not used anymore. Deleting...", productID);
        }
    }

    private void updateProduct(ProductEntity productEntity, ProductDto productDto, boolean delete) {
        int newCount;
        if (delete) {
            newCount = productEntity.getProductCount() - productDto.getCount();
        } else {
            newCount = productEntity.getProductCount() + productDto.getCount();
        }
        productEntity.setProductCount(newCount);
        productRepository.save(productEntity);
        LOGGER.info("Product {} updated.", productEntity.getProductID());
    }

    private ProductEntity createNewProduct(final ProductDto productDto) {
        final ProductEntity productEntity = new ProductEntity.ProductBuilder()
                .withProductID(productDto.getProductId())
                .withCount(productDto.getCount())
                .build();
        productRepository.save(productEntity);
        LOGGER.info("Product {} created.",  productEntity.getProductID());
        return productEntity;
    }
}