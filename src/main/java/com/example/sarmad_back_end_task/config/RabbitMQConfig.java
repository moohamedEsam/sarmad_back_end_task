package com.example.sarmad_back_end_task.config;

import com.example.sarmad_back_end_task.order.model.Order;
import com.example.sarmad_back_end_task.order.service.OrderService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "orders.queue";
    public static final String DEAD_QUEUE_NAME = "orders.dead.queue";
    public static final String EXCHANGE_NAME = "exchangeQueue";
    public static final String ROUTE_KEY = "somethingRandom";

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", DEAD_QUEUE_NAME)
                .build();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTE_KEY);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, OrderService orderService) {
        var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        var interceptor = RetryInterceptorBuilder.stateless()
                .maxAttempts(3)
                .recoverer((message, _) -> {
                    var id = new String(message.getBody());
                    orderService.setOrderStatus(id, Order.OrderStatus.FAILED).block();
                })
                .build();
        factory.setAdviceChain(interceptor);
        return factory;
    }

}
