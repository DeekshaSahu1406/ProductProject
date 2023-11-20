package com.product.pojos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="confirmationToken")
@Data
public class ConfirmationToken{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    public ConfirmationToken() {}

    public ConfirmationToken(Product product) {
        this.product = product;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }



}
