package com.example.gaba.intelligence_homeapp;

public class User {

    private int user_id;
    private String username;
    private String password;
    private String role;
    private boolean hasProfile = false;
    /**
     * Constructor of class User
     */
    public User() {

    }
    /**
     * Constructor of class User
     * @param username
     * @param password
     * @param role
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    /**
     * Constructor of class user
     *
     * @param user_id String
     * @param username String
     * @param password String
     * @param role String
     */
    public User(int user_id, String username, String password, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    /**
     * getter method of User id information
     * @return String role
     */
    public int getUser_id(){ return user_id;}
    public String getRole(){
        return role;
    }
    /**
     * getter method of User password information
     * @return String password
     */
    public String getPassword(){
        return password;
    }
    /**
     * getter method of User password information
     * @return usermame String
     */
    public String getUsername(){
        return username;
    }
    /**
     * set method of User id
     * @param i integer
     */
    public void setUser_id(int i){ user_id = i;}
    /**
     * set method of User Role
     * @param r String
     */
    public void setRole(String r){
        role = r;
    }
    /**
     * set method of User Password
     * @param p String
     */
    public void setPassword(String p){password = p;}
    /**
     * set method of User Name
     * @param u String
     */
    public void setUsername(String u){
        username=u;
    }

    public boolean getHasProfile() {
        return hasProfile;
    }

    public void setHasProfile(boolean b){
        hasProfile=b;
    }

    public String toString(){
        return username;
    }
}
