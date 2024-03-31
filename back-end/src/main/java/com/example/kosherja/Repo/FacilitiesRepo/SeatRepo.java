package com.example.kosherja.Repo.FacilitiesRepo;

import com.example.kosherja.Model.Facilities.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SeatRepo extends MongoRepository<Seat,String> {
Optional<Seat> findById(String id);
Seat findSeatById(String id);
}
