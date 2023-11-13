package com.product.pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="product")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Product extends BaseEntity{

    @Column(length = 20)
    private String name;

    @Column(length = 50)
    private String details;

    @Column
    private double price;

    @Column
    private double discount;
}
