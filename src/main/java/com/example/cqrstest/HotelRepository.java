package com.example.cqrstest;


import com.example.cqrstest.models.HotelDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends MongoRepository<HotelDao, String> {
}
