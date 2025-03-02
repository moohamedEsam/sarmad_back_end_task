package com.example.sarmad_back_end_task.order.api.dto;

import com.example.sarmad_back_end_task.order.model.Order;
import lombok.Data;

@Data
public class OrderDto {

    String customerName;
    String productName;
    float quantity;
    Order.OrderStatus status;
    String id;

    public static OrderDto fromOrder(Order order) {
        var dto = new OrderDto();
        dto.setStatus(order.getStatus());
        dto.setId(order.getId());
        dto.setQuantity(order.getQuantity());
        dto.setCustomerName(order.getCustomerName());
        dto.setProductName(order.getProductName());
        return dto;
    }
}
