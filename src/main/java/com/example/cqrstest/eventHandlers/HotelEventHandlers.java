package com.example.cqrstest.eventHandlers;

import com.example.cqrstest.HotelRepository;
import com.example.cqrstest.events.HotelCreatedEvent;
import com.example.cqrstest.events.ReviewAddedEvent;
import com.example.cqrstest.models.Hotel;
import com.example.cqrstest.models.HotelDao;
import com.example.cqrstest.models.Review;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelEventHandlers {

    @Autowired
    private HotelRepository hotelRepository;

    @EventHandler
    public void on(HotelCreatedEvent event) {
        hotelRepository.save(new HotelDao(event.getEventId(), event.getHotelName(),
                event.getPricePerNight(), event.getAddress(), event.getReviews(), event.getOccuredOn()));
    }

    @EventHandler
    public void on(ReviewAddedEvent event) {
        if(hotelRepository.exists(event.getId())){
            HotelDao hotelDao = hotelRepository.findOne(event.getId());
            List<Review> reviews = hotelDao.getReviews();
            reviews.add(event.getReview());
            hotelDao.setReviews(reviews);
            hotelRepository.save(hotelDao);
        }
    }
}
