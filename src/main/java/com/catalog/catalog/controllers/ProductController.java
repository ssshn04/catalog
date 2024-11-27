package com.catalog.catalog.controllers;

import com.catalog.catalog.entities.Product;
import com.catalog.catalog.requests.ProductRequest;
import com.catalog.catalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")  // Основний шлях для всіх ендпоїнтів
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET запит для отримання всіх товарів
    @GetMapping  // Відповідає /api/products
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // GET запит для отримання товару за ID
    @GetMapping("/{productId}")  // Відповідає /api/products/{productId}
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public String createProduct(@RequestBody ProductRequest productRequest) {
        System.out.println("Request: " + productRequest);
        productService.createProduct(productRequest);
        return "Product is Created successfully!";
    }
}
