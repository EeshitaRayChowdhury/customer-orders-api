package com.customerorders.controller;

import com.customerorders.dto.CreateOrderRequest;
import com.customerorders.dto.OrderDTO;
import com.customerorders.dto.UpdateOrderRequest;
import com.customerorders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public Page<OrderDTO> getOrdersForCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return orderService.getOrdersForCustomer(customerId, page, size);
    }

    @PostMapping("/customers/{customerId}/orders")
    public OrderDTO createOrderForCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CreateOrderRequest request
    ) {
        return orderService.createOrderForCustomer(customerId, request);
    }


    @PutMapping("/customers/{customerId}/orders/{orderId}")
    public OrderDTO updateOrderForCustomer(
            @PathVariable Long customerId,
            @PathVariable Long orderId,
            @Valid @RequestBody UpdateOrderRequest request
    ) {
        return orderService.updateOrderForCustomer(customerId, orderId, request);
    }

    @DeleteMapping("/customers/{customerId}/orders/{orderId}")
    public void deleteOrder(
            @PathVariable Long customerId,
            @PathVariable Long orderId
    ) {
        orderService.deleteOrder(customerId, orderId);
    }

    @GetMapping("/orders")
    public Page<OrderDTO> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return orderService.getOrdersByDateRange(startDate, endDate, page, size);
    }
}