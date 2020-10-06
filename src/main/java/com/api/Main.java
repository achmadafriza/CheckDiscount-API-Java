package com.api;

import com.api.controller.CheckDiscount;
//import com.api.controller.OTP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CheckDiscount.class),
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = OTP.class)
})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
