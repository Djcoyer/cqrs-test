package com.example.cqrstest.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {

    @JsonProperty("username")
    private String username;
    @JsonProperty("rating")
    private int rating;

    public Review(){}

    public Review(String username, int rating){
        this.username = username;
        this.rating = rating;
    }

}
