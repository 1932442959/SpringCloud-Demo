package com.jasfair.orderservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jasfair.feign.entity.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<OrderInfo> {
}
