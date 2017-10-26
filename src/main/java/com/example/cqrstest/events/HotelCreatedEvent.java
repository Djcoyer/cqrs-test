package com.example.cqrstest.events;

import com.example.cqrstest.models.Address;
import com.example.cqrstest.models.Review;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Data
public class HotelCreatedEvent {
    private String eventId;
    private LocalDateTime occuredOn;
    private String hotelName;
    private int pricePerNight;
    private Address address;
    private List<Review> reviews;

    public HotelCreatedEvent(){}

    public HotelCreatedEvent(String eventId, String hotelName, int pricePerNight, Address address, List<Review> reviews){
        this.eventId = eventId;
        this.hotelName = hotelName;
        this.occuredOn = LocalDateTime.now(ZoneId.of("UTC"));
        this.pricePerNight = pricePerNight;
        this.address = address;
        this.reviews = reviews;
    }
}
