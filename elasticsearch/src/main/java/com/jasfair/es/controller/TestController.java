package com.jasfair.es.controller;

import com.jasfair.es.util.SpringUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    private RestHighLevelClient client = SpringUtils.getBean("rest_high_level_client");

    @GetMapping("/")
    public String testSpring() {
        System.out.println(client);
        return "success";
    }
}
