package com.example.cqrstest.services;

import com.example.cqrstest.HotelRepository;
import com.example.cqrstest.aggregates.HotelAggregate;
import com.example.cqrstest.models.Hotel;
import com.example.cqrstest.models.HotelDao;
import com.example.cqrstest.transformers.HotelTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelQueryService {

    @Autowired
    HotelRepository repository;
    private HotelTransformer transformer = new HotelTransformer();
    private List<HotelAggregate> allHotels;

    public HotelQueryService(HotelRepository repository){
        this.repository = repository;
    }

    public Hotel getHotel(String id) {
        HotelDao hotelDao = repository.findOne(id);
        Hotel hotel;
        if(hotelDao != null) {
            hotel = transformer.transform(hotelDao);
            return hotel;
        }
        else{
            return null;
        }

    }

    public List<Hotel> getAllHotels() {
        List<HotelDao> hotelDaos = repository.findAll();
        List<Hotel> hotels = new ArrayList<>();
        for(HotelDao hotelDao : hotelDaos){
            hotels.add(transformer.transform(hotelDao));
        }
        return hotels;
    }
}
