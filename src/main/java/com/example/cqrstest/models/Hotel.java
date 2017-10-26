package com.example.cqrstest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Hotel {
    private String id;
    private String name;
    private int pricePerNight;
    @JsonProperty("address")
    private Address address;
    private List<Review> reviews;

    public Hotel(){}

    public Hotel(String name, int pricePerNight, Address address, List<Review> reviews){
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.address = address;
        this.reviews = reviews;
        this.id = UUID.randomUUID().toString();
    }
}
