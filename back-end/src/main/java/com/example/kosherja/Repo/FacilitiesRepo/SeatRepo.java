package com.example.kosherja.Repo.FacilitiesRepo;

import com.example.kosherja.Model.Facilities.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeatRepo extends MongoRepository<Seat,String> {
}
