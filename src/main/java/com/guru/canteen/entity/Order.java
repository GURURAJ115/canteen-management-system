package com.guru.canteen.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // Use the new table name
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Vendor vendor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "order_items",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @Column(nullable = false)
    private double totalAmount;

    // Constructors
    public Order() {}

    public Order(Customer customer, Vendor vendor) {
        this.customer = customer;
        this.vendor = vendor;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public void addItem(Item item) {
        items.add(item);
    }
}
