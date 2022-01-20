package com.jasfair.userservice.controller;

import com.jasfair.feign.clients.OrderClient;
import com.jasfair.feign.entity.OrderInfo;
import com.jasfair.userservice.config.PatternProperties;
import com.jasfair.userservice.config.PrefixProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

//    @Resource
//    private RestTemplate restTemplate;

//    @Value("${pattern.dateformat}")
//    private String dateformat;

    @Resource
    private PatternProperties patternProperties;

    @Resource
    private PrefixProperties prefixProperties;

    @Resource
    private OrderClient orderClient;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    public String testUser() {
        return "test user success";
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/get")
//    public String getOrder() {
//        String forObject = restTemplate.getForObject("http://order-service/order/getOrder", String.class);
//        return "return: " + forObject;
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/now")
    public String now() {
        return prefixProperties.getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String getOrder() {
        return "get by feign: " + orderClient.getOrder();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOrderById")
    public OrderInfo getOrderById(@RequestParam(name = "id") Long id) {
        return orderClient.getOrderById(id);
    }
}

