package com.jasfair.orderservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasfair.feign.entity.OrderInfo;
import com.jasfair.orderservice.mapper.OrderMapper;
import com.jasfair.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {

    @Override
    public OrderInfo getOneById(Long id) {
        LambdaQueryWrapper<OrderInfo> wrapper = Wrappers.lambdaQuery(OrderInfo.class);
        wrapper.eq(OrderInfo::getId, id);
        return this.getOne(wrapper);
    }
}
