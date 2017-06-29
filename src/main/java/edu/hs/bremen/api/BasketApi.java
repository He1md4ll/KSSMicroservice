package edu.hs.bremen.api;

import edu.hs.bremen.facade.IApiFacade;
import edu.hs.bremen.model.dto.BasketEntryDto;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.validation.BasketValidator;
import edu.hs.bremen.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/basket", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasketApi {

    private IApiFacade apiFacade;
    private Validator productValidator;

    @Autowired
    public BasketApi(IApiFacade apiFacade, BasketValidator basketValidator) {
        this.apiFacade = apiFacade;
        this.productValidator = basketValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(productValidator);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ResponseEntity addBasketEntryToOrder(@RequestParam("user") String userUuid,
                                                @RequestBody @Valid BasketEntryDto basketEntryDto) {
        UserValidator.validateUserId(userUuid);
        final OrderDto orderDto = apiFacade.linkBasketEntry(userUuid, basketEntryDto);
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteBasketEntryOfOrder(@RequestParam("user") String userUuid,
                                               @RequestBody @Valid BasketEntryDto basketEntryDto) {
        UserValidator.validateUserId(userUuid);
        final OrderDto orderDto = apiFacade.deleteBasketEntry(userUuid, basketEntryDto);
        return ResponseEntity.ok(orderDto);
    }
}