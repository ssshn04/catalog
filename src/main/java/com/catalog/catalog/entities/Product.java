package com.catalog.catalog.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "color", length = 50)
    private String color;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "rating")
    private Byte rating = 0;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "stock_available", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean stockAvailable = true;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


}