package com.guru.canteen.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
//    @Column(nullable = false) // Adding the name field
    private String name;

    // Constructors
    public Vendor() {}

    public Vendor(Long id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = username;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
