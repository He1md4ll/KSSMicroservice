package edu.hs.bremen.manager;

import edu.hs.bremen.model.Order;
import edu.hs.bremen.model.Product;
import edu.hs.bremen.model.User;
import edu.hs.bremen.model.dto.ProductDto;
import edu.hs.bremen.repository.ProductRepository;
import edu.hs.bremen.validation.exception.ProductNotLinkedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductManager {

    private OrderManager orderManager;
    private ProductRepository productRepository;

    @Autowired
    public ProductManager(OrderManager orderManager, ProductRepository productRepository) {
        this.orderManager = orderManager;
        this.productRepository = productRepository;
    }

    public Product getProduct(ProductDto productDto) {
        return Optional.ofNullable(productRepository
                .findByProductID(productDto.getProductId()))
                .orElse(createNewProduct(productDto));
    }

    public Order addProductToOrder(User user, Product product) {
        final Order order = orderManager.getOrder(user);
        order.addProduct(product);
        orderManager.saveOrder(order);
        return order;
    }

    public void deleteProductFromOrder(User user, Product product) {
        final Order order = orderManager.getOrder(user);
        Boolean deleteted = order.deleteProduct(product);
        if (deleteted) {
            orderManager.saveOrder(order);
            cleanProduct(product.getProductID());
        } else {
            throw new ProductNotLinkedException();
        }
    }

    public List<Product> getProductsForOrder(User user) {
        return orderManager.getOrder(user).getProductList();
    }

    private void cleanProduct(final String productID) {
        final Product product = productRepository.findByProductID(productID);
        if (product.getOrderList().isEmpty()) {
            productRepository.delete(product);
        }
    }

    private Product createNewProduct(final ProductDto productDto) {
        final Product product = new Product.ProductBuilder()
                .withProductID(productDto.getProductId())
                .build();
        productRepository.save(product);
        return product;
    }
}