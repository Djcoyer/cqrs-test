package com.example.cqrstest.commands;


import com.example.cqrstest.models.Hotel;
import lombok.Data;

@Data
public class CreateHotelCommand {
    private Hotel hotel;

    public CreateHotelCommand(Hotel hotel){
        this.hotel = hotel;
    }

    public Hotel getHotel(){
        return this.hotel;
    }
}
