package com.buk.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * TODO: RabbitMQ 监听
 *
 * @author BuK
 * @date 2020/02/25
 */
@Slf4j
@Component
public class DemoListener {

    /**
     * 1. 监听Queue
     *
     * @param message
     */
    @RabbitListener(queues = "testQueue1")
    public void listener1(String message) {
        log.info("DemoListenerRabbitMQ: -listener1 {}", message);
    }

    /**
     * 2. 自动创建监听Queue
     *
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("testQueue2"))
    public void listener2(String message) {
        log.info("DemoListenerRabbitMQ: -listener2 {}", message);
    }

    /**
     * 3. 自动创建监听Queue并绑定Exchange
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("testExchange3"),
            value = @Queue("testQueue3")
    ))
    public void listen3(String message) {
        log.info("DemoListenerRabbitMQ: -listen3 {}", message);
    }

    /**
     * [4,5]. 自动创建监听Queue并绑定Exchange
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("testExchange[4,5]"),
            key = "testQueue4Key",
            value = @Queue("testQueue4_1")
    ))
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("testExchange[4,5]"),
            key = "testQueue5Key",
            value = @Queue("testQueue4_1")
    ))
    public void listen4_1(String message) {
        log.info("DemoListenerRabbitMQ: -listen4_1 {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("testExchange[4,5]"),
            key = "testQueue4Key",
            value = @Queue("testQueue4_2")
    ))
    public void listen4_2(String message) {
        log.info("DemoListenerRabbitMQ: -listen4_2 {}", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("testExchange[4,5]"),
            key = "testQueue5Key",
            value = @Queue("testQueue5")
    ))
    public void listen5(String message) {
        log.info("DemoListenerRabbitMQ: -listen5 {}", message);
    }
}
