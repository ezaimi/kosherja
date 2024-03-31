package com.example.kosherja.Repo.FacilitiesRepo;

import com.example.kosherja.Model.Facilities.CinemaRoom;
import com.example.kosherja.Service.Cinema.CinemaRoomService;
import org.springframework.data.mongodb.core.MongoAdminOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRoomRepo extends MongoRepository<CinemaRoom,String> {
}
