package com.example.cqrstest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Address implements Serializable {

    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;

    public Address(){}

    public Address(String city, String country){
        this.city = city;
        this.country = country;
    }

}
