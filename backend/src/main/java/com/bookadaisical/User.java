package com.bookadaisical;

public class User {
    private String username;
    private int id;
    private String email;

    public User() {
        // Default constructor
    }

    public User(String username, int id, String email) {
        this.username = username;
        this.id = id;
        this.email = email;
    }

    // Getter and setter methods for the fields
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}