package com.guru.canteen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.canteen.entity.Order;
import com.guru.canteen.entity.Vendor;
import com.guru.canteen.repository.OrderRepository;
import com.guru.canteen.repository.VendorRepository;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Vendor registerVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public String login(String username, String password) {
        Vendor vendor = vendorRepository.findByUsername(username);
        if (vendor != null && vendor.getPassword().equals(password)) {
            return "Login successful!";
        }
        return "Invalid username or password!";
    }

    public String logout(String username) {
        return "Vendor " + username + " logged out successfully!";
    }

    public List<Order> viewOrders() {
        return orderRepository.findAll();
    }

	public List<Order> viewPendingOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	public String acceptOrRejectOrder(Long orderId, boolean accept) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	 // Get the currently logged-in vendor
    public Vendor getCurrentVendor() {
        // Assuming there's logic to fetch the currently logged-in vendor
        // You would typically fetch the logged-in user via security context
        // Here we assume vendor with ID 1 for demonstration purposes
        return vendorRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    // Get a vendor by ID
    public Vendor getVendorById(Long vendorId) {
        return vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }
}