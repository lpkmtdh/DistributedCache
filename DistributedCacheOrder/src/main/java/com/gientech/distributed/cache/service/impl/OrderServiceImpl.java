package com.gientech.distributed.cache.service.impl;

import com.gientech.distributed.cache.cache.Cache;
import com.gientech.distributed.cache.domain.Order;
import com.gientech.distributed.cache.pojo.Result;
import com.gientech.distributed.cache.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 16:52
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    @Qualifier("cacheLocal")
    private Cache cacheLocal;
    @Override
    public Result<Order> createOrder(Order order) {
        // TODO 未使用事务！！！
        // 1. 二级缓存校验Account
        if(cacheLocal.getAccountByUserId(order.getUserId()) == null){
            return Result.unauthorized(null);
        }
        // 2. 下单
        System.out.println("下单成功！");
        return Result.success(order);
    }
}