package com.example.coupenservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
public class CustomSecurityConfigAdapter extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsServiceImpl userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // http.httpBasic();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET,
                        "/couponapi/coupons/**","/index",
                        "/getcoupon",
                        "/getcouponDetails"
                        )
                .hasAnyRole("USER","ADMIN")

                .mvcMatchers(HttpMethod.GET,
                        "/showcreatecoupon",
                        "/createResponse"
                        )
                .hasAnyRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/couponapi/coupons","/savecoupon").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,

                        "/getcouponDetails")
                .hasAnyRole("USER","ADMIN")
                .mvcMatchers("/login","/").permitAll()
                .anyRequest().denyAll()
                .and().csrf().disable()
                .logout().logoutSuccessUrl("/").invalidateHttpSession(false);
        //http.csrf().disable();
        //http.formLogin();
        //http.authorizeRequests().anyRequest().authenticated();
        //http.authorizeRequests().antMatchers("/hello").authenticated();
        //http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);

    }

    @Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
