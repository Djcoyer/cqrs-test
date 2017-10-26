package com.example.cqrstest.models;

public class Review {
    private String userName;
    private int rating;
    private boolean approved;

    public Review(){}

    public Review(String userName, int rating, boolean approved){
        this.userName = userName;
        this.rating = rating;
        this.approved = approved;
    }

}
