package com.bookadaisical;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "bookadaisical", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "current_points")
    private int currentPoints;

    @Column(name = "total_points")
    private int totalPoints;

    @Column(name = "special_currency")
    private int specialCurrency;

    public User() {}

    public User(int id, String username, String email, String password, int currentPoints, int totalPoints,
            int specialCurrency) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.currentPoints = currentPoints;
        this.totalPoints = totalPoints;
        this.specialCurrency = specialCurrency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getSpecialCurrency() {
        return specialCurrency;
    }

    public void setSpecialCurrency(int specialCurrency) {
        this.specialCurrency = specialCurrency;
    }
    
}