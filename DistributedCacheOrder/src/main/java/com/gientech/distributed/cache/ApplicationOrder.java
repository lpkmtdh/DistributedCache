package com.gientech.distributed.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName Application
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 15:28
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients
public class ApplicationOrder {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationOrder.class,args);
    }
}