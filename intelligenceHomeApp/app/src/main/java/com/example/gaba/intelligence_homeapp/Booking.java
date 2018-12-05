package com.example.gaba.intelligence_homeapp;

import android.support.v7.app.AppCompatActivity;

public class Booking  extends AppCompatActivity {

    String serviceProvider;
    String homeOwner;
    String time;
    String serviceName;

    public Booking(){
        serviceProvider = "";
        homeOwner = "";
        time = "";
        serviceName = "";
    }

    public Booking(String sp, String home, String t, String sn){
        serviceProvider = sp;
        homeOwner = home;
        time = t;
        serviceName = sn;
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



    public String toString(){
        return homeOwner + " booked "+serviceName+" service with " + serviceProvider+" at " + time;
    }

    public boolean equals(Booking b){
        if (b.getServiceName().equals(serviceName) && b.getBookingTime().equals( time) && b.getHomeOwner().equals(homeOwner) && b.getServiceName().equals( serviceProvider))
            return true;
        return false;
    }

}
