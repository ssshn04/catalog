package com.catalog.catalog.services;
import com.catalog.catalog.entities.Category;
import com.catalog.catalog.entities.Country;
import com.catalog.catalog.entities.Product;
import com.catalog.catalog.repos.CountryRepository;
import com.catalog.catalog.repos.ProductRepository;
import com.catalog.catalog.repos.CategoryRepository;
import com.catalog.catalog.requests.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private CountryRepository countryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }
    public void createProduct(ProductRequest productRequest) {
        try {
            Country country = countryRepository.findById(productRequest.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found with ID: " + productRequest.getCountryId()));

            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productRequest.getCategoryId()));

            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .color(productRequest.getColor())
                    .country(country)
                    .imageUrl(productRequest.getImageUrl())
                    .stockAvailable(productRequest.getStockAvailable())
                    .category(category)
                    .build();

            productRepository.save(product);

            log.info("Product {} is saved", product.getProductId());
        } catch (Exception e) {
            log.error("Failed to save product: ", e);
        }
    }
}