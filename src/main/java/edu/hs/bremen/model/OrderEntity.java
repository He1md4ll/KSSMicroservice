package edu.hs.bremen.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private UserEntity userEntity;
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductEntity> productEntityList;

    private OrderEntity(){}

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    private void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }

    public void addProduct(ProductEntity productEntity) {
        productEntityList.add(productEntity);
    }

    public Boolean deleteProduct(ProductEntity productEntity) {
        return productEntityList.remove(productEntity);
    }

    private void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public static class OrderBuilder {
        private OrderEntity orderEntity = new OrderEntity();

        public OrderBuilder withUser(UserEntity userEntity) {
            orderEntity.setUserEntity(userEntity);
            return this;
        }

        public OrderBuilder withProductList(List<ProductEntity> productEntityList) {
            orderEntity.setProductEntityList(productEntityList);
            return this;
        }

        public OrderBuilder withNewProductList() {
            orderEntity.setProductEntityList(Lists.newArrayList());
            return this;
        }

        public OrderEntity build() {
            return orderEntity;
        }
    }
}
