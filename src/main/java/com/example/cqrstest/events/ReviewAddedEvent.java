package com.example.cqrstest.events;

import com.example.cqrstest.models.Review;
import lombok.Data;

@Data
public class ReviewAddedEvent {
    public String id;
    public Review review;

    public ReviewAddedEvent(){}

    public ReviewAddedEvent(String id, Review review) {
        this.id = id;
        this.review = review;
    }
}
