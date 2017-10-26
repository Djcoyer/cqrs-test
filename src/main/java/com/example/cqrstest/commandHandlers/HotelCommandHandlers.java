package com.example.cqrstest.commandHandlers;


import com.example.cqrstest.HotelRepository;
import com.example.cqrstest.aggregates.HotelAggregate;
import com.example.cqrstest.commands.AddReviewCommand;
import com.example.cqrstest.commands.CreateHotelCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HotelCommandHandlers {

    @Autowired
    HotelRepository repository;

    public HotelCommandHandlers(HotelRepository repository){

        this.repository = repository;
    }

    public void HandleCommand(CreateHotelCommand command) {
        if(command != null) {
            HotelAggregate aggregate = new HotelAggregate(command);
        }
    }

    public void HandleCommand(AddReviewCommand command) {
        if (command != null) {

        }
    }

}
