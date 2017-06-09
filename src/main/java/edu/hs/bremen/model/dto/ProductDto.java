package edu.hs.bremen.model.dto;

import com.google.common.base.Preconditions;
import edu.hs.bremen.model.ProductEntity;
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

    public ProductDto() {
    }

    public ProductDto(String productId, Integer count) {
        Preconditions.checkNotNull(productId);
        Preconditions.checkNotNull(count);
        this.productId = productId;
        this.count = count;
    }

    public static ProductDto fromProduct(ProductEntity productEntity) {
        return new ProductDto(productEntity.getProductID(), productEntity.getProductCount());
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}