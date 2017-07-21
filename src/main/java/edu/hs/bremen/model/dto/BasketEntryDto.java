package edu.hs.bremen.model.dto;

import edu.hs.bremen.model.BasketEntryEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BasketEntryDto {

    @NotNull
    private ProductDto productDto;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer count;

    public BasketEntryDto() {
        // Empty constructor for automatic marshaling from json
    }

    public BasketEntryDto(ProductDto productDto, Integer count) {
        this.productDto = productDto;
        this.count = count;
    }

    public static BasketEntryDto fromBasketEntry(BasketEntryEntity basketEntryEntity) {
        final ProductDto productDto = ProductDto.fromProduct(basketEntryEntity.getProductEntity());
        return new BasketEntryDto(productDto, basketEntryEntity.getProductCount());
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public Integer getCount() {
        return count;
    }
}