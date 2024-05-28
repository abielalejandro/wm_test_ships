package com.rgarcia.w2m.application.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@Order(1)
public class RequestFilter implements Filter {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange topic;

    @Value("${rabbitmq.routing}")
    String routing;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        StringBuilder builder = new StringBuilder(
                String.format("Starting a transaction for req : %s",req.getRequestURI())
        );
        String message = builder.toString();
        template.convertAndSend(topic.getName(), routing, message);

        chain.doFilter(request, response);
    }
}
