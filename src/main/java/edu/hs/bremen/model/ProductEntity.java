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

    @NotNull
    private Integer productCount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntityList;

    private ProductEntity() {}

    public String getProductID() {
        return productID;
    }

    private void setProductID(String productID) {
        this.productID = productID;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public List<OrderEntity> getOrderEntityList() {

        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public static class ProductBuilder {
        private ProductEntity productEntity = new ProductEntity();

        public ProductBuilder withProductID(String produtID) {
            productEntity.setProductID(produtID);
            return this;
        }

        public ProductBuilder withCount(Integer count) {
            productEntity.setProductCount(count);
            return this;
        }

        public ProductEntity build() {
            return productEntity;
        }
    }
}