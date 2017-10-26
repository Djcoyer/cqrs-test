package com.example.cqrstest.commands;

import com.example.cqrstest.models.Review;
import lombok.Data;

@Data
public class AddReviewCommand {
    private String hotelId;
    private Review review;

    public AddReviewCommand(){}

    public AddReviewCommand(String hotelId, Review review){
        this.hotelId = hotelId;
        this.review = review;
    }
}
