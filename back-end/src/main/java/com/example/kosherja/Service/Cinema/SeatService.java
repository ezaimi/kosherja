package com.example.kosherja.Service.Cinema;

import com.example.kosherja.Model.Facilities.*;
import com.example.kosherja.Repo.FacilitiesRepo.MovieRepo;
import com.example.kosherja.Repo.FacilitiesRepo.ReservationRepo;
import com.example.kosherja.Repo.FacilitiesRepo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatRepo seatRepo;
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    MovieRepo movieRepo;



    public List<Seat> getAllSeats(){
        return seatRepo.findAll();
    }
    public Seat generateSeats(String id,Seat seat,String movieId) {
        Seat seat1=new Seat();
        seat1.setStdId(id);
        seat1.setColumn(seat.getColumn());
        seat1.setRow(seat.getRow());
        seat1.setStatus(SeatStatus.AVAILABLE);
        seat1.setMovieId(movieId);
       return seatRepo.save(seat1);
    }

//    public Reservation reserveSeats(Reservation reservation) {
//      Reservation newReservation=new Reservation();
//        List<String>seatIdList=reservation.getSeatIdList();
//        List<Seat>listseat=new ArrayList<>();
//        for(String seatId:seatIdList){
//            Seat seat=seatRepo.findById(seatId).orElse(null);
//            seat.setStatus(SeatStatus.RESERVED);
//            seatRepo.save(seat);
//            listseat.add(seat);
//
//        }
//        Movie movie=movieRepo.findMovieNameById(movieId).orElse(null);
//        newReservation.setMovieNamee(movie.getMovieName());
//        newReservation.setMovieId(movieId);
//        newReservation.setSeat(listseat);
//        newReservation.setSeatIdList(seatIdList);
//        newReservation.setDateTime(reservation.getDateTime());
//
//        return  reservationRepo.save(newReservation);
//
//    }

}
