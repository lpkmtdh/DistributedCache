package com.gientech.distributed.cache.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gientech.distributed.cache.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
