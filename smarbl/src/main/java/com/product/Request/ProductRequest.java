package com.product.Request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    private String details;

    private double price;

    private double discount;


}
