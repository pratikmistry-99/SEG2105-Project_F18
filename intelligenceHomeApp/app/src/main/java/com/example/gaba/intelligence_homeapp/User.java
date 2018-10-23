package com.example.gaba.intelligence_homeapp;

public class User {

    private String username;
    private String password;
    private String role;

    public User() {

    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
