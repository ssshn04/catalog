package com.catalog.catalog.services;
import com.catalog.catalog.entities.Category;
import com.catalog.catalog.entities.Country;
import com.catalog.catalog.entities.Product;
import com.catalog.catalog.repos.CountryRepository;
import com.catalog.catalog.repos.ProductRepository;
import com.catalog.catalog.repos.CategoryRepository;
import com.catalog.catalog.requests.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;


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
}