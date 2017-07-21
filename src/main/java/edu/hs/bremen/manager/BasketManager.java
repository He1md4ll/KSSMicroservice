package edu.hs.bremen.manager;

import edu.hs.bremen.model.BasketEntryEntity;
import edu.hs.bremen.model.OrderEntity;
import edu.hs.bremen.model.ProductEntity;
import edu.hs.bremen.model.UserEntity;
import edu.hs.bremen.model.dto.BasketEntryDto;
import edu.hs.bremen.repository.BasketEntryRepository;
import edu.hs.bremen.validation.exception.ProductNotLinkedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasketManager.class.getSimpleName());
    private BasketEntryRepository basketEntryRepository;
    private ProductManager productManager;
    private OrderManager orderManager;

    @Autowired
    public BasketManager(BasketEntryRepository basketEntryRepository, ProductManager productManager, OrderManager orderManager) {
        this.basketEntryRepository = basketEntryRepository;
        this.productManager = productManager;
        this.orderManager = orderManager;
    }

    public BasketEntryEntity getUpdatedBasketEntry(final UserEntity userEntity, final BasketEntryDto basketEntryDto, boolean delete) {
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        final ProductEntity productEntity = productManager.getProduct(basketEntryDto.getProductDto().getProductId());
        Optional<BasketEntryEntity> basketEntryEntityOptional = Optional.ofNullable(basketEntryRepository
                .findByProductEntityAndOrderEntity(productEntity, orderEntity));

        if (basketEntryEntityOptional.isPresent()) {
            updateBasketEntry(basketEntryEntityOptional.get(), basketEntryDto, delete);
        } else {
            basketEntryEntityOptional = Optional.of(createNewBasketEntry(productEntity,
                    orderEntity, basketEntryDto.getCount()));
        }
        return basketEntryEntityOptional.orElse(null);
    }

    public OrderEntity addBasketEntryToOrder(UserEntity userEntity, BasketEntryEntity basketEntryEntity) {
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);

        if (orderEntity.getBasketEntryEntityList().contains(basketEntryEntity)) {
            LOGGER.debug("Basket entry for product {} already part of order.", basketEntryEntity.getProductEntity().getProductID());
        } else {
            orderEntity.addBasketEntry(basketEntryEntity);
            orderManager.saveOrder(orderEntity);
            LOGGER.info("Basket entry for product {} added to order.", basketEntryEntity.getProductEntity().getProductID());
        }
        return orderEntity;

    }

    public void deleteBasketEntryFromOrder(UserEntity userEntity, BasketEntryEntity basketEntryEntity) {
        final OrderEntity orderEntity = orderManager.getOrder(userEntity);
        Boolean deleteted = orderEntity.deleteBasketEntry(basketEntryEntity);
        if (deleteted) {
            orderManager.saveOrder(orderEntity);
            basketEntryRepository.delete(basketEntryEntity);
            LOGGER.info("Basket entry for product {} deleted from order.", basketEntryEntity.getProductEntity().getProductID());
        } else {
            throw new ProductNotLinkedException();
        }
    }

    private BasketEntryEntity createNewBasketEntry(ProductEntity productEntity, OrderEntity orderEntity, Integer count) {
        return new BasketEntryEntity.BasketEntryEntityBuilder()
                .withProductEntity(productEntity)
                .withOrderEntity(orderEntity)
                .withProductCount(count)
                .build();
    }

    private void updateBasketEntry(BasketEntryEntity basketEntryEntity, BasketEntryDto basketEntryDto, boolean delete) {
        int newCount;
        if (delete) {
            newCount = basketEntryEntity.getProductCount() - basketEntryDto.getCount();
        } else {
            newCount = basketEntryEntity.getProductCount() + basketEntryDto.getCount();
        }
        basketEntryEntity.setProductCount(newCount);
        basketEntryRepository.save(basketEntryEntity);
        LOGGER.info("Basket entry for product {} updated.", basketEntryEntity.getProductEntity().getProductID());
    }
}