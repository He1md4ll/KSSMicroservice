package edu.hs.bremen.model.dto;

import com.google.common.base.Preconditions;
import edu.hs.bremen.model.Product;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductDto {

    @NotNull
    @NotEmpty
    private String productId;

    @NotNull
    @Min(1)
    @Max(20)
    private Integer count;

    public ProductDto(String productId, Integer count) {
        Preconditions.checkNotNull(productId);
        Preconditions.checkNotNull(count);
        this.productId = productId;
        this.count = count;
    }

    public static ProductDto fromProduct(Product product) {
        return new ProductDto(product.getProductID(), product.getCount());
    }

    public String getProductId() {
        return productId;
    }

    public Integer getCount() {
        return count;
    }
}