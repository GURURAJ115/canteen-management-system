package com.guru.canteen.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guru.canteen.entity.Order;
import com.guru.canteen.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Create a new order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

//    // Update order status
//    @PutMapping("/{orderId}")
//    public Order updateOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
//        return orderService.updateOrder(orderId, updatedOrder);
//    }
//
//    // Reject an order
//    @PutMapping("/{orderId}/reject")
//    public Order rejectOrder(@PathVariable Long orderId) {
//        return orderService.rejectOrder(orderId);
//    }
}
