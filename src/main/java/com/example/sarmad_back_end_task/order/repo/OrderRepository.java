package com.example.sarmad_back_end_task.order.repo;

import com.example.sarmad_back_end_task.order.model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
}
