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

    public Booking(String sp, String home){
        serviceProvider = home;
        homeOwner = home;
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


}
