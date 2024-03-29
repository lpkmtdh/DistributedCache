package com.gientech.distributed.cache.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "t_order")
public class Order {
    @TableId(value = "orderId", type = IdType.AUTO)
    private Long orderId;
    @TableField(value = "userId")
    private Integer userId;
    @TableField(value = "orderTime")
    private Date orderTime;
    @TableField(value = "goodId")
    private Long goodId;
}