package edu.hs.bremen;

import edu.hs.bremen.model.Order;
import edu.hs.bremen.model.Product;
import edu.hs.bremen.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager {

    private OrderManager orderManager;

    @Autowired
    public ProductManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public Order addProductToOrder(User user, Product product) {
        final Order order = orderManager.findOrder(user);
        order.addProduct(product);
        orderManager.saveOrder(order);
        return order;
    }

    public List<Product> getProductsForOrder(User user) {
        return orderManager.findOrder(user).getProductList();
    }
}