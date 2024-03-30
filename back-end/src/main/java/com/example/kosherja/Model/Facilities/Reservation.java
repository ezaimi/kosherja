package com.example.kosherja.Model.Facilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Document(collection = "reservation")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    @Id
    private  String id;
    private String movieId;
    private String movieNamee;
    private LocalDate dateTime;

    private List<Seat> seat;
    private List<String>seatIdList;

    public List<String> getSeatIdList() {
        return seatIdList;
    }

    public void setSeatIdList(List<String> seatIdList) {
        this.seatIdList = seatIdList;
    }

    public List<Seat> getSeat() {
        return seat;
    }

    public String getMovieNamee() {
        return movieNamee;
    }

    public void setMovieNamee(String movieNamee) {
        this.movieNamee = movieNamee;
    }

    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}
