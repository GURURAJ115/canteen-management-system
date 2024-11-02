package com.guru.canteen.service;

import org.springframework.stereotype.Service;

import com.guru.canteen.entity.Customer;
import com.guru.canteen.entity.Item;
import com.guru.canteen.entity.Order;
import com.guru.canteen.entity.Vendor;
import com.guru.canteen.repository.CustomerRepository;
import com.guru.canteen.repository.ItemRepository;
import com.guru.canteen.repository.OrderRepository;
import com.guru.canteen.repository.VendorRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final VendorRepository vendorRepository; // Add VendorRepository
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, 
                        ItemRepository itemRepository, VendorRepository vendorRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.vendorRepository = vendorRepository; // Initialize VendorRepository
    }
    public Order placeOrder(Long customerId, Long vendorId, List<Long> itemIds) {
        // Fetch customer by ID
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Fetch vendor by ID
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        // Fetch items by IDs
        List<Item> items = itemRepository.findAllById(itemIds);
        if (items.isEmpty()) {
            throw new IllegalArgumentException("No items found for the given IDs");
        }

        // Calculate the total amount based on the items
        double totalAmount = items.stream().mapToDouble(Item::getPrice).sum();

        // Create a new order and associate it with the customer, vendor, and items
        Order order = new Order();
        order.setCustomer(customer);
        order.setVendor(vendor);
        order.setItems(items);
        order.setTotalAmount(totalAmount);

        // Save the order to the database
        return orderRepository.save(order);
    }



	public List<Order> getAllOrders() {
	    return orderRepository.findAll();
	}

	public Order createOrder(Order order) {
	    // Validate that the customer exists
	    if (order.getCustomer() == null || order.getCustomer().getId() == null) {
	        throw new IllegalArgumentException("Order must have a valid customer.");
	    }
	    Customer customer = customerRepository.findById(order.getCustomer().getId())
	            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

	    // Validate that the vendor exists
	    if (order.getVendor() == null || order.getVendor().getId() == null) {
	        throw new IllegalArgumentException("Order must have a valid vendor.");
	    }
	    Vendor vendor = vendorRepository.findById(order.getVendor().getId())
	            .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

	    // Validate that the order has items
	    if (order.getItems() == null || order.getItems().isEmpty()) {
	        throw new IllegalArgumentException("Order must contain at least one item.");
	    }

	    // Fetch all items by their IDs and verify they exist
	    List<Long> itemIds = order.getItems().stream().map(Item::getId).toList();
	    List<Item> items = itemRepository.findAllById(itemIds);
	    if (items.isEmpty()) {
	        throw new IllegalArgumentException("No valid items found for the order.");
	    }

	    // Set the customer, vendor, and items to the order
	    order.setCustomer(customer);
	    order.setVendor(vendor);
	    order.setItems(items);

	    // Save the order to the database
	    return orderRepository.save(order);
	}
	public List<Order> getSimpleOrderDetails() {
        List<Order> orders = orderRepository.findAll();

        // Reduce each order to customer username and item names
        for (Order order : orders) {
            order.getCustomer().setPassword(null); // Hide sensitive info
            order.setVendor(null);                 // Exclude vendor details
            order.setTotalAmount(0);               // Hide total amount

            // Retain only item names, exclude other item details
            for (Item item : order.getItems()) {
                item.setPrice(0); // Hide price info
                item.setVendor(null); // Exclude vendor info on items
            }
        }

        return orders;
    }
}
