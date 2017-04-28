package edu.hs.bremen.model;

public class Product {

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