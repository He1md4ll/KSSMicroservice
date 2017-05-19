package edu.hs.bremen.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String productID;

    private Product() {}

    public String getProductID() {
        return productID;
    }

    private void setProductID(String productID) {
        this.productID = productID;
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