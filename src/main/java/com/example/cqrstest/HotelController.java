package com.example.cqrstest;

import com.example.cqrstest.aggregates.HotelAggregate;
import com.example.cqrstest.commandHandlers.HotelCommandHandlers;
import com.example.cqrstest.commands.CreateHotelCommand;
import com.example.cqrstest.models.Hotel;
import com.example.cqrstest.services.HotelQueryService;
import com.google.gson.Gson;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public void addHotel(@RequestBody Hotel hotel) {
        if(hotel != null){
            hotel.setId(UUID.randomUUID().toString());
            CreateHotelCommand command = new CreateHotelCommand(hotel);
            commandGateway.send(command);
        }
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
    public List<HotelAggregate> getAll(){
        return null;
    }

}
