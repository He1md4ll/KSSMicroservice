package edu.hs.bremen.api;

import edu.hs.bremen.facade.IApiFacade;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.ProductDto;
import edu.hs.bremen.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductApi {

    private IApiFacade apiFacade;

    @Autowired
    public ProductApi(IApiFacade apiFacade) {
        this.apiFacade = apiFacade;
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ResponseEntity addProductToOrder(@RequestParam("user") String userUuid,
                                            @RequestBody @Valid ProductDto productDto) {
        UserValidator.validateUserId(userUuid);
        final OrderDto orderDto = apiFacade.linkProduct(userUuid, productDto);
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteProductOfOrder(@RequestParam("user") String userUuid,
                                               @RequestBody @Valid ProductDto productDto) {
        UserValidator.validateUserId(userUuid);
        apiFacade.deleteProduct(userUuid, productDto);
        return ResponseEntity.ok().build();
    }
}