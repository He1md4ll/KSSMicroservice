package edu.hs.bremen.manager;

import edu.hs.bremen.model.ProductEntity;
import edu.hs.bremen.repository.ProductRepository;
import edu.hs.bremen.validation.exception.ProductNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductManager.class.getSimpleName());
    private ProductRepository productRepository;

    @Value("${custom.create.products}")
    private Boolean createProducts;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity getProduct(final String productId) {
        return Optional.ofNullable(productRepository
                .findByProductID(productId))
                .orElseGet(() -> createNewProduct(productId));
    }

    public void deleteProduct(ProductEntity productEntity) {
        if (productEntity.getBasketEntryEntityList().isEmpty()) {
            productRepository.delete(productEntity);
            LOGGER.info("Product {} deleted.", productEntity.getProductID());
        } else {
            LOGGER.debug("Can not delete product {}. Product still in use.", productEntity.getProductID());
        }
    }

    private ProductEntity createNewProduct(final String productId) {
        if (createProducts) {
            final ProductEntity productEntity = new ProductEntity.ProductBuilder()
                    .withProductID(productId)
                    .build();
            productRepository.save(productEntity);
            LOGGER.info("Product {} created.",  productEntity.getProductID());
            return productEntity;
        } else {
            LOGGER.info("Product creation disabled on configuration. Throwing exception...");
            throw new ProductNotValidException();
        }
    }
}