package com.rgarcia.w2m.application.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitConsumer {

    @RabbitListener(queues = "#{queue.name}")
    public void receiveMessage(String message) {
        log.info("Recibiendo mensaje de rabbitmq <"+  message+">");
    }
}
