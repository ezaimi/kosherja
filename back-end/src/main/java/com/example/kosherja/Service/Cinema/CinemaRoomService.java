package com.example.kosherja.Service.Cinema;

import com.example.kosherja.Model.Facilities.CinemaRoom;
import com.example.kosherja.Repo.FacilitiesRepo.CinemaRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CinemaRoomService {
    @Autowired
    CinemaRoomRepo cinemaRoomRepo;
    public CinemaRoom createCinemaRoom(CinemaRoom cinemaRoom){
        return cinemaRoomRepo.save(cinemaRoom);
    }
}
