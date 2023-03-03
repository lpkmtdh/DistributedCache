package com.gientech.distributed.cache.pojo;

import lombok.Data;

/**
 * @ClassName MqMessage
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-03 08:16
 * @Version 1.0
 */
@Data
public class MqMessage {
    private Integer userId;
    private String type;
}