package com.jasfair.userservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:prefix.properties")
public class PrefixProperties {
    @Value("${user.name}")
    private String name;
}
