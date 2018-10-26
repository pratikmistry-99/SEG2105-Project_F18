package com.example.gaba.intelligence_homeapp;

import java.util.ArrayList;

public class Users {
    public static ArrayList<User> users = new ArrayList<>();

    /**
     * method adds new User to data base
     * @param u Usser
     */
    public static void addUser(User u){
        users.add(u);
    }
    /**
     * getter method for finding User in data base
     * @param username
     * @return User
     */
    public static User getUser(String username){
        for(int i = 0; i < users.size(); i++){
            if(username.equals(users.get(i).getUsername()))
                return (User)users.get(i);
        }
        return null;
    }
    /**
     * getter method for returning Users Password
     * @param password
     * @return String
     */
    public static User getPass(String password){
        for (int i = 0; i< users.size(); i++){
            if (password.equals(users.get(i).getPassword()))
                return (User)users.get(i);
        }
        return null;
    }

}
