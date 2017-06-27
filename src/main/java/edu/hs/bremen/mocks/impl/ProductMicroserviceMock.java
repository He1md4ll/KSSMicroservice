package edu.hs.bremen.mocks.impl;

import com.google.common.collect.Lists;
import edu.hs.bremen.mocks.IProductMicroserviceMock;
import edu.hs.bremen.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductMicroserviceMock implements IProductMicroserviceMock {

    @Override
    public List<ProductDto> loadAllProducts() {
        // Add communication to product micro service to load all products
        String[] productIdArray = new String[]{"kiwis", "blueberries", "cherries", "potatoes", "tomatoes", "rhubarb"};
        return Lists.transform(Arrays.asList(productIdArray), ProductDto::new);
    }

    @Override
    public boolean verifyProduct(ProductDto productDto) {
        // Add communication to product micro service here for product validation
        return Boolean.TRUE;
    }
}