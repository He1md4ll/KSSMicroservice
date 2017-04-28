package edu.hs.bremen.model;

import com.google.common.collect.Lists;

import java.util.List;

public class Order {

    private User user;
    private List<Product> productList;

    private Order(){}

    public List<Product> getProductList() {
        return productList;
    }

    private void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    private void setUser(User user) {
        this.user = user;
    }

    public static class OrderBuilder {
        private Order order = new Order();

        public OrderBuilder withUser(User user) {
            order.setUser(user);
            return this;
        }

        public OrderBuilder withProductList(List<Product> productList) {
            order.setProductList(productList);
            return this;
        }

        public OrderBuilder withNewProductList() {
            order.setProductList(Lists.newArrayList());
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
