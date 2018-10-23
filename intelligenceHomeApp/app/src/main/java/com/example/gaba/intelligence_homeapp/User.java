package com.example.gaba.intelligence_homeapp;

public class User {

    private String username;
    private String password;
    private String role;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
