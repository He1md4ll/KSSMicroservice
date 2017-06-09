package edu.hs.bremen.api;

import edu.hs.bremen.facade.IApiFacade;
import edu.hs.bremen.model.dto.OrderDto;
import edu.hs.bremen.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderApi {

    private IApiFacade apiFacade;

    @Autowired
    public OrderApi(IApiFacade apiFacade) {
        this.apiFacade = apiFacade;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity getOrder(@RequestParam("user") String userUuid) {
        UserValidator.validateUserId(userUuid);
        final OrderDto orderDto = apiFacade.getOrder(userUuid);
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@RequestParam("user") String userUuid) {
        UserValidator.validateUserId(userUuid);
        apiFacade.deleteOrder(userUuid);
        return ResponseEntity.ok().build();
    }
}