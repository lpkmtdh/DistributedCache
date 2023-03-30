package com.gientech.distributed.cache.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 16:48
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;
}