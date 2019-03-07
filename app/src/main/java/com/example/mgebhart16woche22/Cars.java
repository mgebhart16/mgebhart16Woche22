package com.example.mgebhart16woche22;

public class Cars {
    String first_name;
    String last_name;
    String hersteller;
    String model;

    public Cars(String first_name, String last_name, String hersteller, String model) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.hersteller = hersteller;
        this.model = model;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return first_name + ", " + last_name + ", " + hersteller + ", " + model;
    }
}
