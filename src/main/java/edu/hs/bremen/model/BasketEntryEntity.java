package edu.hs.bremen.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
public class BasketEntryEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private ProductEntity productEntity;

    @NotNull
    @Max(10)
    private Integer productCount;

    public BasketEntryEntity() {
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public static class BasketEntryEntityBuilder {
        private BasketEntryEntity basketEntryEntity = new BasketEntryEntity();

        public BasketEntryEntityBuilder withProductEntity(ProductEntity productEntity) {
            basketEntryEntity.setProductEntity(productEntity);
            return this;
        }

        public BasketEntryEntityBuilder withProductCount(Integer productCount) {
            basketEntryEntity.setProductCount(productCount);
            return this;
        }

        public BasketEntryEntity build() {
            return basketEntryEntity;
        }
    }
}