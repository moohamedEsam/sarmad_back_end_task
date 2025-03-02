package com.example.sarmad_back_end_task.order.service;

import com.example.sarmad_back_end_task.order.model.Order;
import com.example.sarmad_back_end_task.order.producer.OrderProducer;
import com.example.sarmad_back_end_task.order.repo.OrderRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
    public final OrderRepository repository;
    public final OrderProducer producer;
    private final ReactiveMongoTemplate template;

    public OrderService(OrderRepository repository, OrderProducer producer, ReactiveMongoTemplate template) {
        this.repository = repository;
        this.producer = producer;
        this.template = template;
    }

    public Mono<UpdateResult> setOrderStatus(String id, Order.OrderStatus status) {
        var query = new Query(Criteria.where("_id").is(id));
        var update = new Update().set("status", status);
        return template.updateFirst(query, update, Order.class);
    }

    public Mono<Order> insert(Order order) {
        return repository.save(order)
                .doOnSuccess(res -> producer.send(res.getId()));
    }


    public Mono<Order> getById(String id) {
        return repository.findById(id);
    }

}
