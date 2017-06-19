package edu.hs.bremen.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class ProductEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String productID;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BasketEntryEntity> basketEntryEntityList;

    private ProductEntity() {}

    public String getProductID() {
        return productID;
    }

    private void setProductID(String productID) {
        this.productID = productID;
    }

    public List<BasketEntryEntity> getBasketEntryEntityList() {
        return basketEntryEntityList;
    }

    public void setBasketEntryEntityList(List<BasketEntryEntity> basketEntryEntityList) {
        this.basketEntryEntityList = basketEntryEntityList;
    }

    public static class ProductBuilder {
        private ProductEntity productEntity = new ProductEntity();

        public ProductBuilder withProductID(String produtID) {
            productEntity.setProductID(produtID);
            return this;
        }

        public ProductEntity build() {
            return productEntity;
        }
    }
}