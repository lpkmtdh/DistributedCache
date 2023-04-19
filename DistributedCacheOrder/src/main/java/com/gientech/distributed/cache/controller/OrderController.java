package com.gientech.distributed.cache.controller;

import com.gientech.distributed.cache.domain.Order;
import com.gientech.distributed.cache.pojo.Result;
import com.gientech.distributed.cache.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName OrderController
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 16:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private Environment environment;

    @PostMapping
    public Result<Order> createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }

    @GetMapping
    public Result<Order> createOrder2(@RequestParam("userId") Integer userId){
        Order order = new Order(1L,userId,new Date(),100L);
        return orderService.createOrder(order);
    }

    @GetMapping("/seata")
    public Result<Order> createOrder3(@RequestParam("uid") Integer uid){
        return orderService.createOrder3(uid);
    }

    @GetMapping("/port")
    public Result<String> getPort(){
        return Result.success(environment.getProperty("local.server.port"));
    }
}