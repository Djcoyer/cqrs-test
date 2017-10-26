package com.example.cqrstest.aggregates;

import com.example.cqrstest.commands.AddReviewCommand;
import com.example.cqrstest.commands.CreateHotelCommand;
import com.example.cqrstest.events.HotelCreatedEvent;
import com.example.cqrstest.events.ReviewAddedEvent;
import com.example.cqrstest.models.Address;
import com.example.cqrstest.models.Hotel;
import com.example.cqrstest.models.Review;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Data
@Aggregate
public class HotelAggregate extends AbstractAggregateRoot {

    @AggregateIdentifier
    private String id;
    private String name;
    private int pricePerNight;
    private Address address;
    private List<Review> reviews;
    private LocalDateTime createdDate;

    @CommandHandler
    public HotelAggregate(CreateHotelCommand cmd){
        Assert.hasLength(cmd.getHotel().getName());
        Hotel hotel = cmd.getHotel();
        if(hotel.getName() != null) {
            apply(new HotelCreatedEvent(hotel.getId(), hotel.getName(),
                    hotel.getPricePerNight(), hotel.getAddress(), hotel.getReviews()));
        }
    }

    @CommandHandler
    public HotelAggregate(AddReviewCommand cmd) {
        Assert.notNull(cmd);
        apply(new ReviewAddedEvent(cmd.getHotelId(), cmd.getReview()));
    }

    @EventSourcingHandler
    public void on(HotelCreatedEvent event){
        this.id = event.getEventId();
        this.name = event.getHotelName();
        this.pricePerNight = event.getPricePerNight();
        this.address = event.getAddress();
        this.createdDate = event.getOccuredOn();
        this.reviews = event.getReviews();
    }

    @EventSourcingHandler
    public void on(ReviewAddedEvent event) {
        this.id = event.getId();
        System.out.println("Event added: " +event.getId());
    }
}
