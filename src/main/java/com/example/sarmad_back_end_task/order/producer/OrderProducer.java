package com.example.sarmad_back_end_task.order.producer;

import com.example.sarmad_back_end_task.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private final AmqpTemplate template;

    public OrderProducer(AmqpTemplate template) {
        this.template = template;
    }

    public void send(String id) {
        template.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTE_KEY,
                id
        );
    }
}
