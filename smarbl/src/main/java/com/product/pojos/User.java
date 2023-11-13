package com.product.pojos;

import jakarta.persistence.Column;
import lombok.*;

//@Entity
//@Table(name="user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {

//    @NotBlank(message = "Username is required")
//    @Column(length = 50)
    private String email;

//    @NotBlank(message = "Password is required")
//@Column(length = 50)
    private String password;


}
