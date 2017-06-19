package edu.hs.bremen.mocks;

import edu.hs.bremen.model.dto.ProductDto;

import java.util.List;

public interface IProductMicroserviceMock {
    List<ProductDto> loadAllProducts();
    boolean verifyProduct(ProductDto productDto);
}