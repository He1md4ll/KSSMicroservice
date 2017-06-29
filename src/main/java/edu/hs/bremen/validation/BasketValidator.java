package edu.hs.bremen.validation;

import edu.hs.bremen.mocks.IProductMicroserviceMock;
import edu.hs.bremen.model.dto.BasketEntryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BasketValidator implements Validator {

    private IProductMicroserviceMock productMicroserviceMock;

    @Autowired
    public BasketValidator(IProductMicroserviceMock productMicroserviceMock) {
        this.productMicroserviceMock = productMicroserviceMock;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BasketEntryDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final BasketEntryDto basketEntryDto = (BasketEntryDto) target;
        if (!productMicroserviceMock.verifyProduct(basketEntryDto.getProductDto())) {
            errors.rejectValue("productId", "invalid.productId");
        }
    }
}