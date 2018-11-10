package com.example.gaba.intelligence_homeapp;

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

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public void setName(String name){this.name = name;}

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String toString(){
        return getName() + ", " + getRate() + " $/hour";
    }
}
