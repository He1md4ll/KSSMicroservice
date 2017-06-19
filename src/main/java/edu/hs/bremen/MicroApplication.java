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

	@Bean
	private void initProducts(ProductRepository productRepository,
							  IProductMicroserviceMock productMicroserviceMock) {
	    // TODO: Add validator for product
        // Initialize products
		for (ProductDto productDto : productMicroserviceMock.loadAllProducts()) {
			final ProductEntity productEntity = new ProductEntity.ProductBuilder()
					.withProductID(productDto.getProductId())
					.build();
			productRepository.save(productEntity);
		}
	}
}