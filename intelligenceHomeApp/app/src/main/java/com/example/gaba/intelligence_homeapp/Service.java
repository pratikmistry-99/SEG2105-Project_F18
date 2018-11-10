package com.example.gaba.intelligence_homeapp;

public class Service {

    private String name;
    private int rate;

    public Service(){
        name = null;
        rate = 0;
    }

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

    public void setName(String name){this.name = name;}

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String toString(){
        return getName() + ", " + getRate() + "$/hour";
    }
}
