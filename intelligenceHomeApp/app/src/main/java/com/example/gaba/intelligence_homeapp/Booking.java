package com.example.gaba.intelligence_homeapp;

import android.support.v7.app.AppCompatActivity;

public class Booking  extends AppCompatActivity {

    String serviceProvider;
    String homeOwner;
    String time;
    String serviceName;
    String serviceProvider_name;
    String homeowner_name;

    public Booking(){
        serviceProvider = "";
        homeOwner = "";
        serviceProvider_name = "";
        homeowner_name = "";
        time = "";
        serviceName = "";
    }

    public Booking(String sp, String home, String t, String sn){
        serviceProvider = sp;
        homeOwner = home;
        time = t;
        serviceName = sn;
        serviceProvider_name = "";
        homeowner_name = "";
    }

    public String getServiceProvider(){
        return serviceProvider;
    }

    public String getHomeOwner(){
        return homeOwner;
    }

    public void setServiceProvider(String sp){
        serviceProvider = sp;
    }

    public void setHomeOwner(String home){
        homeOwner = home;
    }

    public String getBookingTime(){
        return time;
    }

    public void setBookingTime(String t){
        time = t;
    }

    public String getServiceName(){
        return serviceName;
    }

    public void setServiceName(String sn){
        serviceName = sn;
    }

    public String getServiceProvideName(){
        return serviceProvider_name;
    }

    public String getHomeOwnerName(){
        return homeowner_name;
    }

    public void setServiceProviderName(String sp){
        serviceProvider_name = sp;
    }

    public void setHomeOwnerName(String home){
        homeowner_name = home;
    }


    public String toString(){

        if(serviceProvider_name.equals("") ||homeowner_name.equals(""))
            return "Service: "+serviceName+ "\nTime: " + time.replace("//", ":")+ "HH";
        else
            return "Service: "+serviceName+"\nService Provider: " + serviceProvider_name
                + "\nHomeowner: " + homeowner_name
                + "\nTime: " + time.replace("//", ":")+ "HH";
    }

    public boolean equals(Booking b){
        if (b.getServiceName().equals(serviceName) && b.getBookingTime().equals( time) && b.getHomeOwner().equals(homeOwner) && b.getServiceName().equals( serviceProvider))
            return true;
        return false;
    }

}
