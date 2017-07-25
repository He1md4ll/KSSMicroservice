package edu.hs.bremen.mocks.impl;

import com.google.common.collect.Lists;
import edu.hs.bremen.mocks.IProductMicroserviceMock;
import edu.hs.bremen.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Object to mock behaviour of product micro service
 * including loading of all available products and verification.
 */
@Component
public class ProductMicroserviceMock implements IProductMicroserviceMock {

    /**
     * Returns list of all available products.
     * No real communication to service => Mock.
     * @return list of products
     */
    @Override
    public List<ProductDto> loadAllProducts() {
        // Add communication to product micro service to load all products
        String[] productIdArray = new String[]{"kiwis", "blueberries", "cherries", "potatoes", "tomatoes", "rhubarb"};
        return Lists.transform(Arrays.asList(productIdArray), ProductDto::new);
    }

    /**
     * Checks if product id is valid.
     * No real communication to service => Mock.
     * @return valid or not
     */
    @Override
    public boolean verifyProduct(ProductDto productDto) {
        // Add communication to product micro service here for product validation
        return Boolean.TRUE;
    }
}