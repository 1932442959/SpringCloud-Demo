package com.jasfair.orderservice.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.jasfair.feign.entity.OrderInfo;

public interface OrderService extends IService<OrderInfo> {
    OrderInfo getOneById(Long id);
}
