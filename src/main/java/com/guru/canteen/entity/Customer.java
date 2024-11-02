package com.guru.canteen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private double walletBalance;
//
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<Order> orders;

    // Constructors
    public Customer() {}

    public Customer(Long id, String username, String password, double walletBalance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.walletBalance = walletBalance;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public double getWalletBalance() { return walletBalance; }
    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }
//    public List<Order> getOrders() { return orders; }
//    public void setOrders(List<Order> orders) { this.orders = orders; }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", walletBalance=" + walletBalance +
                '}';
    }
}