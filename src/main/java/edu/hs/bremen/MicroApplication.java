package edu.hs.bremen;

import edu.hs.bremen.mocks.IProductMicroserviceMock;
import edu.hs.bremen.model.ProductEntity;
import edu.hs.bremen.model.dto.ProductDto;
import edu.hs.bremen.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroApplication.class, args);
	}

	/**
	 * Loads all products from product micro service and saves them to product repo.
	 * @param productRepository repository for products (injected)
	 * @param productMicroserviceMock mock of product service (injected)
	 * @return just needed for bean definition is Spring
	 */
	@Bean
	public String initProducts(ProductRepository productRepository,
							  IProductMicroserviceMock productMicroserviceMock) {
        // Initialize products
		for (ProductDto productDto : productMicroserviceMock.loadAllProducts()) {
			final ProductEntity productEntity = new ProductEntity.ProductBuilder()
					.withProductID(productDto.getProductId())
					.build();
			productRepository.save(productEntity);
		}
		return null;
	}
}