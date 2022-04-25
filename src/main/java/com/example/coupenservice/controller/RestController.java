package com.example.coupenservice.controller;


import com.example.coupenservice.dto.Product;
import com.example.coupenservice.model.Coupon;
import com.example.coupenservice.model.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/couponapi")
public class RestController {

    @Autowired
    CouponRepo couponRepo;

    @Autowired
    private RestTemplate  restTemplate;

    @Value("${productservice.url}")
    private String productServiceUrl;

    @RequestMapping(value = "/coupons",method = RequestMethod.POST)
    public Coupon create(@RequestBody Coupon coupen){
       return  couponRepo.save(coupen);
    }


    @RequestMapping(value = "/coupons/{code}",method = RequestMethod.GET)
    public Coupon getCoupen(@PathVariable("code")  String code){
        return  couponRepo.findByCode(code);
    }

    @RequestMapping(value = "/coupons/product/{id}",method = RequestMethod.GET)
    public Product getCoupen(@PathVariable("id")  Long id){
        Product product =   restTemplate.getForObject(productServiceUrl+id, Product.class );
        return  product;
    }
}
