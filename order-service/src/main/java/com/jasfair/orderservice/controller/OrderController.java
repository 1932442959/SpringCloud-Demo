package com.jasfair.orderservice.controller;

import com.jasfair.feign.clients.OrderClient;
import com.jasfair.feign.entity.OrderInfo;
import com.jasfair.orderservice.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String testOrder() {
        return "test order success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOrder")
    public String getOrder() {
        return "get order success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOrderById")
    public OrderInfo getOrderById(@RequestParam(name = "id") Long id) {
        return orderService.getOneById(id);
    }

}
