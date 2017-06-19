package edu.hs.bremen.mocks.impl;

import com.google.common.collect.Lists;
import edu.hs.bremen.mocks.IProductMicroserviceMock;
import edu.hs.bremen.model.dto.ProductDto;

import java.util.List;

public class ProductMicroserviceMock implements IProductMicroserviceMock {

    @Override
    public List<ProductDto> loadAllProducts() {
        return Lists.newArrayList();
    }

    @Override
    public boolean verifyProduct(ProductDto productDto) {
        // Add communication to product micro service here for product validation
        return Boolean.TRUE;
    }
}