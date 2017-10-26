package com.example.cqrstest.services;

import com.example.cqrstest.HotelRepository;
import com.example.cqrstest.models.Hotel;
import com.example.cqrstest.models.HotelDao;
import com.example.cqrstest.transformers.HotelTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelQueryService {

    @Autowired
    HotelRepository repository;
    private HotelTransformer transformer = new HotelTransformer();

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

}
