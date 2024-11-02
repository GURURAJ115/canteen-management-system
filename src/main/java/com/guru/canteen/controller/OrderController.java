package com.guru.canteen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.guru.canteen.entity.Item;
import com.guru.canteen.entity.Order;
import com.guru.canteen.service.ItemService;
import com.guru.canteen.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final ItemService itemService;
    private final OrderService orderService;

    public OrderController(ItemService itemService, OrderService orderService) {
        this.itemService = itemService;
        this.orderService = orderService;
    }

    @GetMapping("/placeOrder")
    public String showPlaceOrderPage(@RequestParam("customerId") Long customerId,
                                     @RequestParam("vendorId") Long vendorId, Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        model.addAttribute("customerId", customerId);
        model.addAttribute("vendorId", vendorId); // Add vendorId to the model
        return "customer/placeOrder";
    }



    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam("customerId") Long customerId,
                             @RequestParam("vendorId") Long vendorId,
                             @RequestParam("itemIds") List<Long> itemIds, Model model) {
        try {
            Order order = orderService.placeOrder(customerId, vendorId, itemIds);
            model.addAttribute("success", "Order placed successfully!");
            System.out.println(order);
            return "redirect:/customer/menu?customerId=" + customerId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "customer/menu";  // Redirect to menu with error message
        }
    }



    // View all orders
    @GetMapping("/list")
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }
}
