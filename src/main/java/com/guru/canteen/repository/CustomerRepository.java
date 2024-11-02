package com.guru.canteen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.canteen.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    
}