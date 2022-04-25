package com.example.coupenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CoupenServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoupenServiceApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate(){
       return  new RestTemplate();
    }

}
