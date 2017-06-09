package edu.hs.bremen.model.dto;

import com.google.common.base.Preconditions;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserProductWrapper {

    @NotNull
    @Valid
    private UserDto userDto;

    @NotNull
    @Valid
    private ProductDto productDto;

    public UserProductWrapper(UserDto userDto, ProductDto productDto) {
        Preconditions.checkNotNull(userDto);
        Preconditions.checkNotNull(productDto);
        this.userDto = userDto;
        this.productDto = productDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }
}