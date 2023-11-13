package com.product.Response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductResponse {


    private String name;


    private String details;


    private double price;

    private double discount;
}
