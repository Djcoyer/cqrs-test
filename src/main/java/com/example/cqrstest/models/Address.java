package com.example.cqrstest.models;

import java.io.Serializable;

public class Address implements Serializable {

    private String city;
    private String country;

    public Address(){}

    public Address(String city, String country){
        this.city = city;
        this.country = country;
    }

}
