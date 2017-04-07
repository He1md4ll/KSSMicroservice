package edu.hs.bremen.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultOrderSericeImpl implements IOrderSerice {

    @RequestMapping(path = "")
    public void addProductToOrder() {

    }
}