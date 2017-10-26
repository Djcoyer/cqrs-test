package com.example.cqrstest.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection="Hotels")
public class HotelDao {
    private String id;
    private String name;
    private int pricePerNight;
    private Address address;
    private List<Review> reviews;
    private LocalDateTime createdDate;

    public HotelDao(){}

    public HotelDao(String id, String name, int pricePerNight, Address address, List<Review> reviews,
                    LocalDateTime createdDate){
        this.id = id;
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.address =address;
        this.reviews = reviews;
        this.createdDate =createdDate;
    }
}
