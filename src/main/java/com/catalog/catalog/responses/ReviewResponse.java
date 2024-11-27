package com.catalog.catalog.responses;

import lombok.Data;

@Data
public class ReviewResponse {
    private String userName;
    private Byte rating;
    private String comment;
}
