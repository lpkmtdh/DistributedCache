package com.gientech.distributed.cache.service;

import com.gientech.distributed.cache.domain.Order;
import com.gientech.distributed.cache.pojo.Result;

public interface OrderService {
    Result<Order> createOrder(Order order);

    Result<Order> createOrder3(Integer uid);
}
