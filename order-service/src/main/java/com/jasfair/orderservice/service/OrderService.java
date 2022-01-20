package com.jasfair.orderservice.service;


import com.jasfair.feign.entity.OrderInfo;

public interface OrderService {
    OrderInfo getOneById(Long id);
}
