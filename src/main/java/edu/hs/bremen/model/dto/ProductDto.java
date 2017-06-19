package edu.hs.bremen.model.dto;

import edu.hs.bremen.model.ProductEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ProductDto {

    @NotNull
    @NotEmpty
    private String productId;

    public ProductDto() {
    }

    public ProductDto(String productId) {
        this.productId = productId;
    }

    public static ProductDto fromProduct(ProductEntity productEntity) {
        return new ProductDto(productEntity.getProductID());
    }

    public String getProductId() {
        return productId;
    }
}