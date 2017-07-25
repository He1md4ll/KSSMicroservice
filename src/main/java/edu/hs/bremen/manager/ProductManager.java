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

/**
 * Manager to handle all product related operations
 * including data manipulation and creation.
 */
@Service
public class ProductManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductManager.class.getSimpleName());
    private ProductRepository productRepository;

    // Variable to switch between product creation and rejection if not present
    @Value("${custom.create.products}")
    private Boolean createProducts;

    @Autowired
    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Returns corresponding product for product id.
     * If product id not found either product is created or exception is thrown
     * depending on configuration (see custom.create.products in application.properties)
     * @param productId unique id of product
     * @return product
     */
    public ProductEntity getProduct(final String productId) {
        return Optional.ofNullable(productRepository
                .findByProductID(productId))
                .orElseGet(() -> createNewProduct(productId));
    }

    /**
     * Deletes product form repository if not used in any order.
     * @param productEntity product
     */
    public void deleteProduct(ProductEntity productEntity) {
        if (productEntity.getBasketEntryEntityList().isEmpty()) {
            productRepository.delete(productEntity);
            LOGGER.info("Product {} deleted.", productEntity.getProductID());
        } else {
            LOGGER.debug("Can not delete product {}. Product still in use.", productEntity.getProductID());
        }
    }

    /**
     * Creates and saves new product depending on configuration.
     * @param productId unique product id
     * @return new product
     */
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