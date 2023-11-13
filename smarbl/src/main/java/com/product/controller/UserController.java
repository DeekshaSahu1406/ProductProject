package com.product.controller;


import com.product.LoginEnum.LoginCredentials;
import com.product.pojos.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserController {


    @PostMapping("/login")
    public String login(@RequestBody User LoginRequest){
        String username = LoginRequest.getEmail();
        String password = LoginRequest.getPassword();


        if (username.equals(LoginCredentials.USER1.getLoginId()) && password.equals(LoginCredentials.USER1.getPassword())) {
            return "Login Successful";
        } else {
            return "Login denied";
        }


    }

    @GetMapping("/logout")
    public String logout(){

            return "Logout successfull";



    }
}
