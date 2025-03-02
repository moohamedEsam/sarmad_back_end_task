package com.example.sarmad_back_end_task.order.api.request_bodies;

import com.example.sarmad_back_end_task.order.model.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateOrderRequest {
    @NotBlank
    String customerName;
    @NotBlank
    String productName;
    @Positive
    float quantity;

    public Order toOrder(){
        var order = new Order();
        order.setCustomerName(customerName);
        order.setProductName(productName);
        order.setQuantity(quantity);
        return order;
    }
}
