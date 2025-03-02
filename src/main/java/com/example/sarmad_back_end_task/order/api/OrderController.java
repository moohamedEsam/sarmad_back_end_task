package com.example.sarmad_back_end_task.order.api;

import com.example.sarmad_back_end_task.common.model.ApiResult;
import com.example.sarmad_back_end_task.common.model.ApiResultUtils;
import com.example.sarmad_back_end_task.order.api.dto.OrderDto;
import com.example.sarmad_back_end_task.order.api.request_bodies.CreateOrderRequest;
import com.example.sarmad_back_end_task.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ApiResult<OrderDto>> create(@RequestBody @Valid CreateOrderRequest request) {
        return service.insert(request.toOrder())
                .map(OrderDto::fromOrder)
                .map(ApiResultUtils::success);

    }

    @GetMapping("/{id}")
    public Mono<ApiResult<OrderDto>> get(@PathVariable("id") String id) {
        return service.getById(id)
                .map(OrderDto::fromOrder)
                .map(ApiResultUtils::success)
                .defaultIfEmpty(ApiResultUtils.failure("not Found"));
    }
}
