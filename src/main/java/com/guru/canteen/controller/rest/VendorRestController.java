package com.guru.canteen.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.guru.canteen.entity.Vendor;
import com.guru.canteen.service.VendorService;

@RestController
@RequestMapping("/api/vendors")
public class VendorRestController {

    @Autowired
    private VendorService vendorService;

    // Register a new vendor
    @PostMapping("/register")
    public Vendor registerVendor(@RequestBody Vendor vendor) {
        return vendorService.registerVendor(vendor);
    }

    // Get vendor details by ID
    @GetMapping("/{vendorId}")
    public Vendor getVendorById(@PathVariable Long vendorId) {
        return vendorService.getVendorById(vendorId);
    }


    // Login customer
    @PostMapping("/login")
    public String loginCustomer(@RequestParam String username, @RequestParam String password) {
        return vendorService.login(username, password);
    }
}
