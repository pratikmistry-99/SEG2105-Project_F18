package com.example.gaba.intelligence_homeapp;

public class Service {

    private String name;
    private int rate;

    public Service(String name, int rate){

        this.name = name;
        this.rate = rate;

    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
