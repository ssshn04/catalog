package com.catalog.catalog.services;
import com.catalog.catalog.entities.Category;
import com.catalog.catalog.entities.Country;
import com.catalog.catalog.entities.Product;
import com.catalog.catalog.repos.CountryRepository;
import com.catalog.catalog.repos.ProductRepository;
import com.catalog.catalog.repos.CategoryRepository;
import com.catalog.catalog.requests.ProductRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final CountryRepository countryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }
    public Product createProduct(ProductRequest productRequest) {
        Country country = countryRepository.findById(productRequest.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found with ID: " + productRequest.getCountryId()));

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productRequest.getCategoryId()));

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setColor(productRequest.getColor());
        product.setCountry(country);
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockAvailable(productRequest.getStockAvailable());
        product.setCategory(category);

        return productRepository.save(product);
    }

    public List<Product> getRecommendedProducts(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        Category category = product.getCategory();

        return productRepository.findTop5ByCategoryOrderByRatingDesc(category);
    }

    public List<Product> searchProducts(String name, Integer categoryId, Integer countryId) {
        return productRepository.searchProducts(name, categoryId, countryId);
    }

    public boolean isProductInStock(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.isPresent() && product.get().getStockAvailable();
    }
    public BigDecimal getProductPrice(int productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(Product::getPrice).orElse(null);
    }
}