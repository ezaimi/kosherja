package com.example.kosherja.Controller.FacilitiesController;

import com.example.kosherja.Model.Facilities.Reservation;
import com.example.kosherja.Model.Facilities.Seat;
import com.example.kosherja.Service.Cinema.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cinema/seats/{stdId}")
public class SeatController {
    @Autowired
    private SeatService seatService;


//    @PostMapping("/selectSeat")
//    public ResponseEntity<Seat> showAllSeats(@PathVariable String stdId,@PathVariable String movieId,@RequestBody Seat seat){
//
//        return new ResponseEntity<>( seatService.generateSeats(stdId,seat,movieId),HttpStatus.CREATED);
//    }

//    @PostMapping("/reservation/{seatId}")
//    public ResponseEntity<Reservation> reserveSeats(@RequestBody Reservation reservation) {
//        Reservation success = seatService.reserveSeats(reservation);
//        return  new ResponseEntity<>(success,HttpStatus.CREATED);
//    }
}
