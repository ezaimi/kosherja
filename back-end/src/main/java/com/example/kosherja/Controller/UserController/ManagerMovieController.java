package com.example.kosherja.Controller.UserController;

import com.example.kosherja.Model.Facilities.CinemaRoom;
import com.example.kosherja.Model.Facilities.Seat;
import com.example.kosherja.Model.Facilities.SeatStatus;
import com.example.kosherja.Repo.FacilitiesRepo.SeatRepo;
import com.example.kosherja.Service.Cinema.CinemaRoomService;
import com.example.kosherja.Service.Cinema.MovieService;
import com.example.kosherja.Service.Cinema.SeatService;
import com.example.kosherja.Service.UserService.ManagerMovieService;
import com.example.kosherja.Service.UserService.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema/{managerId}")
public class ManagerMovieController {
    @Autowired
    private ManagerMovieService managerMovieService;
    @Autowired
    private MovieService movieService;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    CinemaRoomService cinemaRoomService;

//    1.Assign movie to cinemaRoom and seats are created AUTOMATICLYY
@PostMapping("/{roomId}/{movieId}")
public CinemaRoom assignMovieandTime(@PathVariable String movieId, @RequestBody CinemaRoom cinemaRoom1) {
    // Create a new CinemaRoom instance
    CinemaRoom cinemaRoom = new CinemaRoom(movieId);

    // Retrieve the showtime from the request body
    List<String> time1 = cinemaRoom1.getShowtime();

    // Set the movieId and showtime to the cinemaRoom object
    cinemaRoom.setMovieId(movieId);
    cinemaRoom.setShowtime(time1);

    // Initialize the seats and associate them with the movieId
    Seat[][] seats = new Seat[5][5];
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            Seat seat = new Seat();
            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setRow(i);
            seat.setColumn(j);
            seat.setMovieId(movieId);
            seats[i][j] = seat;
        }
    }
    cinemaRoom.setSeats(seats);

    // Save the seats to the database
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
            seatRepo.save(seats[i][j]);
        }
    }

    // Return the cinemaRoom object
    return cinemaRoomService.createCinemaRoom(cinemaRoom);
}

}
