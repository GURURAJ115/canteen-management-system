package com.guru.canteen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.canteen.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByUsername(String username);
}