package edu.hs.bremen.api;

import edu.hs.bremen.facade.IApiFacade;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.UserProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity addProductToOrder(@RequestBody @Valid UserProductWrapper wrapper) {
        // UserValidator.validateUserId(userId);
        final OrderDto orderDto = apiFacade.linkProduct(wrapper.getUserDto(), wrapper.getProductDto());
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteProductOfOrder(@RequestBody @Valid UserProductWrapper wrapper) {
        // UserValidator.validateUserId(userId);
        apiFacade.deleteProduct(wrapper.getUserDto(), wrapper.getProductDto());
        return ResponseEntity.ok().build();
    }
}