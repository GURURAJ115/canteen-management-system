package com.guru.canteen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.canteen.entity.Customer;
import com.guru.canteen.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Save a new customer to the database
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Authenticate customer credentials
    public String login(String username, String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null && customer.getPassword().equals(password)) {
            return "Login successful!";
        } else {
            throw new RuntimeException("Invalid username or password.");
        }
    }

    // Get customer details by ID
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    public Customer findCustomerByUsername(String username) throws Exception {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new Exception("Customer not found");
        }
        return customer;
    }
    public long getId(Customer customer) {
    	return customer.getId();
    }
}
