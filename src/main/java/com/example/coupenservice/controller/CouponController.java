package com.example.coupenservice.controller;


import com.example.coupenservice.model.Coupon;
import com.example.coupenservice.model.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class CouponController {

    @Autowired
    private CouponRepo couponRepo;

//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }

    @GetMapping("/showcreatecoupon")
    public String showCreateCoupon(){
        return "crateCoupon";
    }

    @GetMapping("/getcoupon")
    public String getCoupon(){
        return "getcoupon";
    }


    @PostMapping("/getcouponDetails")
    public ModelAndView getCoupon(String code ){
       ModelAndView mav=  new ModelAndView("couponDetails");
       mav.addObject(couponRepo.findByCode(code));
        return mav;
    }


    @PostMapping("/savecoupon")
    public String save(Coupon coupon){
        couponRepo.save(coupon);
        return "createResponse";
    }





}
