package com.gientech.distributed.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Order
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 16:48
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long orderId;
    private Integer userId;
    private Date orderTime;
    private Long goodId;
}