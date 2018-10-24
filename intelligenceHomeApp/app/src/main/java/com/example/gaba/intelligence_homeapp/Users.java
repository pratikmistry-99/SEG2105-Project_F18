package com.example.gaba.intelligence_homeapp;

import java.util.ArrayList;

public class Users {
    public static ArrayList<User> users = new ArrayList<>();


    public static void addUser(User u){
        users.add(u);
    }

    public static User getUser(String username){
        for(int i = 0; i < users.size(); i++){
            if(username.equals(users.get(i).getUsername()))
                return (User)users.get(i);
        }
        return null;
    }


}
