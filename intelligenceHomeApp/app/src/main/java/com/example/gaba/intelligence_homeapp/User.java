package com.example.gaba.intelligence_homeapp;

public class User {

    private int user_id;
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

    public User(int user_id, String username, String password, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUser_id(){ return user_id;}
    public String getRole(){
        return role;
    }
    public String getPassword(){
        return password;
    }
    public String getUsername(){
        return username;
    }

    public void setUser_id(int i){ user_id = i;}
    public void setRole(String r){
        role = r;
    }
    public void setPassword(String p){password = p;}
    public void setUsername(String u){
        username=u;
    }

}
