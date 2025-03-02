package com.example.sarmad_back_end_task.order.model;

import com.github.f4b6a3.ulid.UlidCreator;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Order implements Persistable<String> {
    String customerName;
    String productName;
    float quantity;
    OrderStatus status = OrderStatus.PENDING;
    @Id
    String id;

    @Override
    public boolean isNew() {
        if (id == null) {
            id = UlidCreator.getUlid().toString();
            return true;
        }
        return false;
    }

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED
    }
}
