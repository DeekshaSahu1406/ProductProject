package com.product.Request;

import com.product.pojos.BaseEntity;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductRequest extends BaseEntity {

    private String name;

    private String details;

    private double price;

    private double discount;


}
