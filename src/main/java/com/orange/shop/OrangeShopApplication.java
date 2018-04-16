package com.orange.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.orange.shop")
public class OrangeShopApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(OrangeShopApplication.class, args);
    }
}
