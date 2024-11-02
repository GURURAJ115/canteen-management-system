package com.guru.canteen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guru.canteen.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	 @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
	    List<Order> findAllByCustomerId(@Param("customerId") Long customerId);
	 List<Order> findByCustomerId(Long customerId);
}