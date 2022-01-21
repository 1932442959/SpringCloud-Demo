package com.jasfair.publisher.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMsg2SimpleQueue() {
        String queueName = "simple.queue";
        String msg = "hello, spring amqp";
        rabbitTemplate.convertAndSend(queueName, msg);
    }

    @Test
    public void testSendMsg2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String msg = "hello, message__";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, msg + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendConsumerFanoutExchange() {
        String exchangeName = "jasfair.consumer-fanout";
        String message = "hello everyone";
        rabbitTemplate.convertAndSend(exchangeName, null, message);
    }

    @Test
    public void testSendConsumerDirectExchange() {
        String exchangeName = "jasfair.consumer-direct";
        String message = "hello red";
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    @Test
    public void testSendConsumerTopicExchange() {
        String exchangeName = "jasfair.consumer-topic";
        String message = "The weather of China ...";
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }

}
