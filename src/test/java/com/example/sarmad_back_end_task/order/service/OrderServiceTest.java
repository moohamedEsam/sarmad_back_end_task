package com.example.sarmad_back_end_task.order.service;

import com.example.sarmad_back_end_task.order.model.Order;
import com.example.sarmad_back_end_task.order.producer.OrderProducer;
import com.example.sarmad_back_end_task.order.repo.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWebClient
class OrderServiceTest {
    private final OrderRepository repository;
    private OrderService service;
    private Order order;
    private final OrderProducer orderProducer;
    private final ReactiveMongoTemplate template;

    OrderServiceTest(@Autowired OrderRepository repository, @Autowired OrderProducer orderProducer, @Autowired ReactiveMongoTemplate template) {
        this.repository = repository;
        this.orderProducer = orderProducer;
        this.template = template;
    }

    @BeforeEach
    public void setup() {
        service = new OrderService(repository, orderProducer, template);
        order = new Order();
    }

    @Test
    void insert_createOrder_shouldReturn_TheSameValues() {
        order.setProductName("product 1");
        order.setCustomerName("user 1");
        service.insert(order)
                .doOnSuccess(res -> Assertions.assertEquals(order, res))
                .doOnError(Assertions::fail)
                .block();
    }

    @Test
    void getById_idNotFound_shouldReturn_error() {

        var result = service.getById("not found")
                .blockOptional(Duration.ofMillis(100));

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void getById_idFound_shouldReturn_orderAndDeleteIt() {
        service.insert(order).block();
        service.getById(order.getId())
                .doOnError(Assertions::fail)
                .doOnSuccess(res -> Assertions.assertEquals(res, order))
                .block();
    }
}