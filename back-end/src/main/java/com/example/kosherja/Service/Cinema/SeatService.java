package com.example.kosherja.Service.Cinema;

import com.example.kosherja.Model.Facilities.Reservation;
import com.example.kosherja.Model.Facilities.Seat;
import com.example.kosherja.Model.Facilities.SeatStatus;
import com.example.kosherja.Repo.FacilitiesRepo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatRepo seatRepo;
    public List<Seat> getAllSeats(){
        return seatRepo.findAll();
    }
    public Seat generateSeats(int numRows, int numColumns) {
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= numRows; row++) {
            for (int column = 1; column <= numColumns; column++) {
                Seat seat = new Seat();
                seat.setRow(row);
                seat.setColumn(column);
                seat.setStatus(SeatStatus.AVAILABLE); // Assuming all seats are initially available
                seats.add(seat);
            }
        }
       return (Seat) seatRepo.saveAll(seats);
    }

    public boolean reserveSeats(Reservation reservation) {
        List<Seat> seats = reservation.getSeats();

        // Check if all seats are available
        for (Seat seat : seats) {
            Seat retrievedSeat = seatRepo.findById(seat.getId()).orElse(null);
            if (retrievedSeat == null || retrievedSeat.getStatus() != SeatStatus.AVAILABLE) {
                // Seat is not available for reservation
                return false;
            }
        }

        // Update seat statuses and save reservation details
        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.RESERVED);
            seatRepo.save(seat);
        }

        // Return true if seats are successfully reserved
        return true;
    }
}
