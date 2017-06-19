package edu.hs.bremen.api;

import edu.hs.bremen.facade.IApiFacade;
import edu.hs.bremen.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductApi {

    private IApiFacade apiFacade;

    @Autowired
    public ProductApi(IApiFacade apiFacade) {
        this.apiFacade = apiFacade;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity getProduct(@RequestParam("productId") String productId) {
        final ProductDto productDtoResult = apiFacade.getProduct(productId);
        return ResponseEntity.ok(productDtoResult);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@RequestParam("productId") String productId) {
        apiFacade.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}