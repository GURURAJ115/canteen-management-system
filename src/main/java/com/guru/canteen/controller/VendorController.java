package com.guru.canteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.guru.canteen.entity.Item;
import com.guru.canteen.entity.Order;
import com.guru.canteen.entity.Vendor;
import com.guru.canteen.service.ItemService;
import com.guru.canteen.service.OrderService;
import com.guru.canteen.service.VendorService;

import java.util.List;

@Controller
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    // Display registration page
    @GetMapping("/register")
    public String showVendorRegisterPage() {
        return "vendor/vendorRegister";
    }

    // Handle vendor registration
    @PostMapping("/register")
    public String registerVendor(@ModelAttribute Vendor vendor, Model model) {
        try {
            Vendor registeredVendor = vendorService.registerVendor(vendor);
            model.addAttribute("message", "Vendor registered successfully with ID: " + registeredVendor.getId());
            return "vendor/vendorLogin"; // Redirect to login after successful registration
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "vendor/vendorRegister"; // Show the registration page again on error
        }
    }

    // Display login page
    @GetMapping("/login")
    public String showVendorLoginPage() {
        return "vendor/vendorLogin";
    }

    // Handle vendor login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            String loginResult = vendorService.login(username, password);
            model.addAttribute("message", loginResult);
            
            // Redirect to dashboard on successful login
            return "redirect:/vendor/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Login failed: " + e.getMessage());
            return "vendor/vendorLogin"; // Show the login page again on error
        }
    }

 // Display vendor dashboard
    @GetMapping("/dashboard")
    public String showVendorDashboard(Model model) {
        List<Item> items = itemService.getAllItems();
        List<Order> orders = orderService.getSimpleOrderDetails(); // Use simplified order details
        model.addAttribute("items", items);
        model.addAttribute("orders", orders);
        return "vendor/dashboard";
    }


    // Update menu item
    @PostMapping("/updateItem")
    public String updateItem(@RequestParam Long itemId, @ModelAttribute Item updatedItem, Model model) {
        itemService.updateItem(itemId, updatedItem);  // Pass itemId to the service
        model.addAttribute("message", "Item updated successfully");
        return "redirect:/vendor/dashboard"; // Reload dashboard after update
    }

//    // Accept order
//    @PostMapping("/acceptOrder")
//    public String acceptOrder(@RequestParam Long orderId, Model model) {
//        orderService.acceptOrder(orderId);
//        model.addAttribute("message", "Order accepted successfully");
//        return "redirect:/vendor/dashboard";
//    }
//
//    // Reject order
//    @PostMapping("/rejectOrder")
//    public String rejectOrder(@RequestParam Long orderId, Model model) {
//        orderService.rejectOrder(orderId);
//        model.addAttribute("message", "Order rejected successfully");
//        return "redirect:/vendor/dashboard";
//    }

//    // Update vendor profile
//    @GetMapping("/profile")
//    public String showProfilePage(Model model) {
//        Vendor vendor = vendorService.getCurrentVendor();
//        model.addAttribute("vendor", vendor);
//        return "vendor/profile";
//    }
//
//    @PostMapping("/updateProfile")
//    public String updateProfile(@ModelAttribute Vendor vendor, Model model) {
//        vendorService.updateVendorProfile(vendor);
//        model.addAttribute("message", "Profile updated successfully");
//        return "vendor/profile";
//    }

    // Display add item page
    @GetMapping("/addItem")
    public String showAddItemPage() {
        return "vendor/addItem";
    }

    // Handle adding an item
    @PostMapping("/addItem")
    public String addItem(@ModelAttribute Item item, Model model) {
        itemService.addMenuItem(item);
        model.addAttribute("message", "Item added successfully!");
        return "redirect:/vendor/dashboard";
    }
}
