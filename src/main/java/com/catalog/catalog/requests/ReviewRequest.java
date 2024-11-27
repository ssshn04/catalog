package com.catalog.catalog.requests;

import lombok.Data;

@Data
public class ReviewRequest {
    private String userName;
    private Byte rating;
    private String comment;
    private Integer productId;
}