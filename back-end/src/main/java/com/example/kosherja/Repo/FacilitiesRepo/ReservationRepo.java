package com.example.kosherja.Repo.FacilitiesRepo;

import com.example.kosherja.Model.Facilities.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepo extends MongoRepository<Reservation,String> {
}
