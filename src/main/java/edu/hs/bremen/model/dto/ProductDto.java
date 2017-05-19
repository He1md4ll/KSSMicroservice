package edu.hs.bremen.model.dto;

import edu.hs.bremen.model.Product;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ProductDto {

    @NotNull
    @NotEmpty
    private String productId;

    public ProductDto(String productId) {
        this.productId = productId;
    }

    public static ProductDto fromProduct(Product product) {
        return new ProductDto(product.getProductID());
    }

    public String getProductId() {
        return productId;
    }
}