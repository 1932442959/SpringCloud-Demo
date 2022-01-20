package com.jasfair.userservice.config;

import lombok.Data;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.introspector.Property;

import java.util.Map;
import java.util.Properties;

@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    private String dateformat; // get from Nacos' yaml
}
