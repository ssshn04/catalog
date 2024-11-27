package com.catalog.catalog.responses;

import com.catalog.catalog.entities.Product;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductResponse {
    private String imageUrl;
    private String name;
    private String description;
    private Byte rating;
    private String color;
    private String countryName;
    private String categoryName;
    private BigDecimal price;


}
