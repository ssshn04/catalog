package com.catalog.catalog.controllers;

import com.catalog.catalog.entities.Product;
import com.catalog.catalog.requests.ProductRequest;
import com.catalog.catalog.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
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
