package com.example.cqrstest.transformers;


import com.example.cqrstest.aggregates.HotelAggregate;
import com.example.cqrstest.models.Hotel;
import com.example.cqrstest.models.HotelDao;

public class HotelTransformer {

    public HotelDao transform(HotelAggregate aggregate){
        HotelDao dao = new HotelDao();
        dao.setAddress(aggregate.getAddress());
        dao.setReviews(aggregate.getReviews());
        dao.setCreatedDate(aggregate.getCreatedDate());
        dao.setId(aggregate.getId());
        dao.setName(aggregate.getName());
        dao.setPricePerNight(aggregate.getPricePerNight());
        return dao;
    }

    public Hotel transform(HotelDao dao){
        Hotel hotel = new Hotel();
        hotel.setId(dao.getId());
        hotel.setAddress(dao.getAddress());
        hotel.setName(dao.getName());
        hotel.setPricePerNight(dao.getPricePerNight());
        hotel.setReviews(dao.getReviews());
        return hotel;
    }
}
