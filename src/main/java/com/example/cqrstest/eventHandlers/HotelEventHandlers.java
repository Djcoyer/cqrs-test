package com.example.cqrstest.eventHandlers;

import com.example.cqrstest.HotelRepository;
import com.example.cqrstest.events.HotelCreatedEvent;
import com.example.cqrstest.models.HotelDao;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelEventHandlers {

    @Autowired
    private HotelRepository hotelRepository;

    @EventHandler
    public void handle(HotelCreatedEvent event) {
        hotelRepository.save(new HotelDao(event.getEventId(), event.getHotelName(),
                event.getPricePerNight(), event.getAddress(), event.getReviews(), event.getOccuredOn()));
    }
}
