package com.ism.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @GetMapping("/auth/login")
    public String authenticate(){
        return "Hello world";
    }

}
