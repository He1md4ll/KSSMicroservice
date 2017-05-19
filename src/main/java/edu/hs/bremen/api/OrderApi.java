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

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ResponseEntity createOrder(@RequestParam(value="userId") String userId) {
        UserValidator.validateUserId(userId);
        final OrderDto orderDto = apiFacade.createOrder(userId);
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity getOrderList(@RequestParam("userId") String userId) {
        UserValidator.validateUserId(userId);
        final OrderDto orderDto = apiFacade.findOrder(userId);
        return ResponseEntity.ok(orderDto);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@RequestParam("userId") String userId) {
        UserValidator.validateUserId(userId);
        final Boolean deleteOrder = apiFacade.deleteOrder(userId);
        if (deleteOrder) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}