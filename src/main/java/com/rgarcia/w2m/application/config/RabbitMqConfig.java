package com.rgarcia.w2m.application.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.exchange}")
    String exchangeName;

    @Value("${rabbitmq.routing}")
    String routing;

    @Bean
    Queue queue() {
        return new AnonymousQueue();
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding binding(TopicExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(routing);
    }
}
