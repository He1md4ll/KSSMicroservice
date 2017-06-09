package edu.hs.bremen.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String productID;

    @NotNull
    private Integer count;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orderList;

    private Product() {}

    public String getProductID() {
        return productID;
    }

    private void setProductID(String productID) {
        this.productID = productID;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Order> getOrderList() {

        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public static class ProductBuilder {
        private Product product = new Product();

        public ProductBuilder withProductID(String produtID) {
            product.setProductID(produtID);
            return this;
        }

        public Product build() {
            return product;
        }
    }
}