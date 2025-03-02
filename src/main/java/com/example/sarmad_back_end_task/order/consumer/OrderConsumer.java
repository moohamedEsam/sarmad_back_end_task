package com.example.sarmad_back_end_task.order.consumer;

import com.example.sarmad_back_end_task.config.RabbitMQConfig;
import com.example.sarmad_back_end_task.order.model.Order;
import com.example.sarmad_back_end_task.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class OrderConsumer {
    private final OrderService service;
    private static final int DELAY = 5;

    public OrderConsumer(OrderService service) {

        this.service = service;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String id) {
        service.setOrderStatus(id, Order.OrderStatus.PROCESSING)
                .delayElement(Duration.ofSeconds(DELAY))
                .flatMap(_ -> service.setOrderStatus(id, Order.OrderStatus.COMPLETED))
                .block();
    }

}
