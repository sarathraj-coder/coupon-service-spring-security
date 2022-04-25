package com.example.coupenservice.controller;


import com.example.coupenservice.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String email,String password){
       boolean resturk =  securityService.login(email,password);
       if(resturk) {
           return "index";
       }else{
           return "login";
       }
    }
}
