package com.guru.canteen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.canteen.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByVendorId(Long vendorId);
}