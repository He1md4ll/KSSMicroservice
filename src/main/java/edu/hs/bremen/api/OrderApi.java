package edu.hs.bremen.api;

import edu.hs.bremen.facade.IApiFacade;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderApi {

    private IApiFacade apiFacade;

    @Autowired
    public OrderApi(IApiFacade apiFacade) {
        this.apiFacade = apiFacade;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity getOrder(@RequestParam("userId") @Valid UserDto userDto) {
        // TODO: Validation
        // UserValidator.validateUserId(userId);
        final OrderDto orderDto = apiFacade.getOrder(userDto);
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@RequestBody @Valid UserDto userDto) {
        // UserValidator.validateUserId(userId);
        apiFacade.deleteOrder(userDto);
        return ResponseEntity.ok().build();
    }
}