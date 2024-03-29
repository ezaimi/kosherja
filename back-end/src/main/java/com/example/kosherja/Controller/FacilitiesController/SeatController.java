package com.example.kosherja.Controller.FacilitiesController;

import com.example.kosherja.Model.Facilities.Reservation;
import com.example.kosherja.Model.Facilities.Seat;
import com.example.kosherja.Service.Cinema.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinema/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/show")
    public ResponseEntity<Seat> showAllSeats(@RequestParam int numRows, @RequestParam int numColumns){

        return new ResponseEntity<>( seatService.generateSeats(numRows,numColumns),HttpStatus.CREATED);
    }
    @PostMapping("/reservation")
    public ResponseEntity<String> reserveSeats(@RequestBody Reservation reservation) {
        boolean success = seatService.reserveSeats(reservation);
        if (success) {
            return new ResponseEntity<>("Seats reserved successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to reserve seats. Some seats might already be reserved.", HttpStatus.BAD_REQUEST);
        }
    }
}
