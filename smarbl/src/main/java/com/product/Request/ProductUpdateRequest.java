package com.product.Request;


import lombok.Data;

@Data
public class ProductUpdateRequest {

    private Long id;

    private String details;

    private double price;
}
