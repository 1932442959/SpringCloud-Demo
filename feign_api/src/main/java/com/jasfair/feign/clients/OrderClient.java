package com.jasfair.feign.clients;

import com.jasfair.feign.entity.OrderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("order-service")
public interface OrderClient {

    @RequestMapping(method = RequestMethod.GET, value = "/order/getOrder")
    String getOrder();

    @RequestMapping(method = RequestMethod.GET, value = "/order/getOrderById")
    OrderInfo getOrderById(@RequestParam(name = "id") Long id);
}
