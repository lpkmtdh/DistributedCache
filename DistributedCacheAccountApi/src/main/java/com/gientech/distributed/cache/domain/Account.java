package com.gientech.distributed.cache.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Account
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 15:41
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer userId;
    private String userName;

}