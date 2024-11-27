package com.catalog.catalog.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private String color;
    private Integer countryId;
    private String imageUrl;
    private Boolean stockAvailable;
    private Integer categoryId;
}