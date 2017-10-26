package com.example.cqrstest;

import com.example.cqrstest.aggregates.HotelAggregate;
import com.example.cqrstest.commandHandlers.HotelCommandHandlers;
import com.example.cqrstest.commands.AddReviewCommand;
import com.example.cqrstest.commands.CreateHotelCommand;
import com.example.cqrstest.models.Hotel;
import com.example.cqrstest.models.HotelDao;
import com.example.cqrstest.models.Review;
import com.example.cqrstest.services.HotelQueryService;
import com.google.gson.Gson;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    HotelCommandHandlers commandHandlers;
    HotelQueryService queryService;
    private final CommandGateway commandGateway;

    public HotelController(CommandGateway commandGateway, HotelCommandHandlers commandHandlers, HotelQueryService queryService){
        this.commandHandlers = commandHandlers;
        this.queryService = queryService;
        this.commandGateway = commandGateway;
    }

    @PostMapping("/add")
    public CompletableFuture<HotelDao> addHotel(@RequestBody Hotel hotel) {
        if(hotel != null){
            hotel.setId(UUID.randomUUID().toString());
            CreateHotelCommand command = new CreateHotelCommand(hotel);
            return commandGateway.send(command);
        }
        else return null;
    }

    @RequestMapping(path = "/{guid}/reviews/add")
    public CompletableFuture addReview(@PathVariable String guid, @RequestBody Review review) {
        if(guid != null && review != null) {
            AddReviewCommand command = new AddReviewCommand(guid,review);
            return commandGateway.send(command);
        }
        else return null;
    }

    @RequestMapping(path = "/{guid}", method = RequestMethod.GET)
    public String getHotel(@PathVariable String guid){
        if(guid != null) {
            Hotel hotel = queryService.getHotel(guid);
            if(hotel != null){
                String hotelString = new Gson().toJson(hotel);
                return hotelString;
            }
            else return null;
        }
        else return null;
    }

    @GetMapping("/all")
    public List<String> getAll(){
        List<Hotel> hotels = queryService.getAllHotels();
        List<String> hotelStrings = new ArrayList<>();
        Gson gson = new Gson();
        for(Hotel hotel : hotels) {
            hotelStrings.add(gson.toJson(hotel));
        }
        return hotelStrings;
    }

}
