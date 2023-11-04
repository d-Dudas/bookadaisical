package com.bookadaisical;

public class User {
    private String username;
    private int id;
    public User() {}

    public User(String username, int id) {
        this.username = username;
        this.id = id;
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
}