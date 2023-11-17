package com.product.Response;

import com.product.pojos.BaseEntity;
import com.product.pojos.Product;
import jakarta.persistence.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse extends BaseEntity {

    private String name;


    private String details;


    private double price;

    private double discount;

    public ProductResponse(Product product) {
        // Constructor logic to convert Product to ProductResponse
        this.name = product.getName();
        this.details = product.getDetails();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        // Map other properties as needed
    }
}
