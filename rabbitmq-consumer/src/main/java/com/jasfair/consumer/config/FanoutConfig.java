package com.jasfair.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    @Bean
    public FanoutExchange consumerFanoutExchange() {
        return new FanoutExchange("jasfair.consumer-fanout");
    }

    @Bean
    public Queue consumerFanoutQueue1() {
        return new Queue("consumer-fanout.queue1");
    }

    @Bean
    public Binding consumerFanoutBinding1(Queue consumerFanoutQueue1, FanoutExchange consumerFanoutExchange) {
        return BindingBuilder.bind(consumerFanoutQueue1).to(consumerFanoutExchange);
    }

    @Bean
    public Queue consumerFanoutQueue2() {
        return new Queue("consumer-fanout.queue2");
    }

    @Bean
    public Binding consumerFanoutBinding2(Queue consumerFanoutQueue2, FanoutExchange consumerFanoutExchange) {
        return BindingBuilder.bind(consumerFanoutQueue2).to(consumerFanoutExchange);
    }
}
