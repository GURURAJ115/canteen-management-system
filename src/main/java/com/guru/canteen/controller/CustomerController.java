package com.guru.canteen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.guru.canteen.entity.Customer;
import com.guru.canteen.entity.Item;
import com.guru.canteen.service.CustomerService;
import com.guru.canteen.service.ItemService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemService itemService;


    // Display place order page
 // Display place order page
    @GetMapping("/placeOrder")
    public String showPlaceOrderPage(@RequestParam("customerId") Long customerId, 
                                     @RequestParam("itemId") Long itemId, 
                                     Model model) {
        model.addAttribute("customerId", customerId);
        model.addAttribute("item", itemService.getItemById(itemId)); // Fetch the item by ID to show on the order page
        return "customer/customerOrder";
    }

    // Handle placing an order
    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam Long customerId, @RequestParam Double totalAmount, Model model) {
        // OrderService.placeOrder(customerId, totalAmount);
        model.addAttribute("message", "Order placed successfully!");
        return "redirect:/customer/orderHistory?customerId=" + customerId; // Include customerId in redirect
    }

//    // Display order history
//    @GetMapping("/orderHistory")
//    public String showOrderHistory(@RequestParam Long customerId, Model model) {
//        model.addAttribute("orders", orderService.getOrdersForCustomer(customerId));
//        return "customer/orderHistory";
//    }
    // Display registration page
    @GetMapping("/register")
    public String showCustomerRegisterPage() {
        return "customer/customerRegister";
    }

    // Handle customer registration
    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {
        try {
            Customer registeredCustomer = customerService.registerCustomer(customer);
            model.addAttribute("message", "Customer registered successfully with ID: " + registeredCustomer.getId());
            return "customer/customerLogin"; // Redirect to login after successful registration
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "customer/customerRegister"; // Show the registration page again on error
        }
    }

    // Display login page
    @GetMapping("/login")
    public String showCustomerLoginPage() {
        return "customer/customerLogin";
    }

 // Handle customer login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            Customer customer = customerService.findCustomerByUsername(username);
            String loginResult = customerService.login(username, password);
            
            model.addAttribute("message", loginResult);
            
            // Redirect to menu page on successful login, including customerId
            return "redirect:/customer/menu?customerId=" + customer.getId();
        } catch (Exception e) {
            model.addAttribute("error", "Login failed: " + e.getMessage());
            return "customer/customerLogin"; // Show the login page again on error
        }
    }


    @GetMapping("/menu")
    public String showMenuPage(@RequestParam("customerId") Long customerId, 
//                               @RequestParam("vendorId") Long vendorId, 
                               Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        model.addAttribute("customerId", customerId);  // Add customerId to the model
//        model.addAttribute("vendorId", vendorId);      // Add vendorId to the model
        return "customer/menu";
    }

}
