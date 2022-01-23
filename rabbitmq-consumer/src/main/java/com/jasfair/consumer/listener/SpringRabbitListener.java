package com.jasfair.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Slf4j
@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String msg) {
//        System.out.println("listen message: { " + msg + " }");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("Consumer first(1) listen: [ " + msg + " ] " + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.out.println("Consumer second(2) listen: [ " + msg + " ] " + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "consumer-fanout.queue1")
    public void listenConsumerFanoutQueue1(String msg) {
        System.out.println("consumer-fanout queue first listen message: { " + msg + " }");
    }

    @RabbitListener(queues = "consumer-fanout.queue2")
    public void listenConsumerFanoutQueue2(String msg) {
        System.out.println("consumer-fanout queue second listen message: { " + msg + " }");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "consumer-direct.queue1"),
            exchange = @Exchange(name = "jasfair.consumer-direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenConsumerDirectQueue1(String msg) {
        System.out.println("consumer-direct queue first listen message: { " + msg + " }");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "consumer-direct.queue2"),
            exchange = @Exchange(name = "jasfair.consumer-direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenConsumerDirectQueue2(String msg) {
        System.out.println("consumer-direct queue second listen message: { " + msg + " }");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("consumer-topic.queue1"),
            exchange = @Exchange(value = "jasfair.consumer-topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenConsumerTopicQueue1(String msg) {
        System.out.println("consumer-topic queue first listen message: { " + msg + " }");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("consumer-topic.queue2"),
            exchange = @Exchange(value = "jasfair.consumer-topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenConsumerTopicQueue2(String msg) {
        System.out.println("consumer-topic queue second listen message: { " + msg + " }");
    }

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String, Object> msg) {
        System.out.println("listem object.queue message: { " + msg + " }");
    }

}
