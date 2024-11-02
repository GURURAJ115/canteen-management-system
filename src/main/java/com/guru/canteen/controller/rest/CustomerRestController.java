package com.guru.canteen.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guru.canteen.entity.Customer;
import com.guru.canteen.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    // Register a new customer
    @PostMapping("/register")
    public Customer registerCustomer(@RequestBody Customer customer) {
        return customerService.registerCustomer(customer);
    }

    // Get customer details by ID
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    // Login customer
    @PostMapping("/login")
    public String loginCustomer(@RequestParam String username, @RequestParam String password) {
        return customerService.login(username, password);
    }
}
