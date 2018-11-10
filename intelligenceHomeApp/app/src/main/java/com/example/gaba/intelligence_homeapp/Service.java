package com.example.gaba.intelligence_homeapp;

// class used to create an instance of a service with properties name and rate
public class Service {

    private String name;
    private Double rate;

    public Service(){
        name = null;
        rate = 0.0;
    }

    public Service(String name, Double rate){

        this.name = name;
        this.rate = rate;

    }

    // returns the name(string)
    public String getName() {
        return name;
    }

    // returns the rate(double)
    public Double getRate() {
        return rate;
    }

    // sets the name instance variable
    public void setName(String name){this.name = name;}

    // sets the rate instance variable
    public void setRate(Double rate) {
        this.rate = rate;
    }

    // returns toString of service
    public String toString(){
        return getName() + ", " + getRate() + " $/hour";
    }
}
