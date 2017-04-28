package edu.hs.bremen.api;

import edu.hs.bremen.model.Product;
import edu.hs.bremen.repository.ProductManager;
import edu.hs.bremen.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultOrderSericeImpl implements IOrderSerice {

    private ProductManager productManager;

    @Autowired
    public DefaultOrderSericeImpl(ProductManager productManager) {
        this.productManager = productManager;
    }

    @RequestMapping(path = "product", method = RequestMethod.PUT)
    public void addProduct(@RequestParam(value="userID") String userID, String productID) {
        if (UserValidator.isUserValid(userID)) {
            // TODO: Add validation for User
            // TODO: uild User
            final Product product = new Product.ProductBuilder().withProductID(productID).build();
            productManager.addProductToOrder(null, product);
        }
    }
}