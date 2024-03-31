package com.example.kosherja.Model.Facilities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cinemaRoom")
@NoArgsConstructor
@AllArgsConstructor
public class CinemaRoom {
    private  Seat[][] seats;
    private String movieeId;
    private List<String> showtime;

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public String getMovieId() {
        return movieeId;
    }

    public void setMovieId(String movieId) {
        this.movieeId = movieId;
    }

    public List<String> getShowtime() {
        return showtime;
    }

    public void setShowtime(List<String> showtime) {
        this.showtime = showtime;
    }

    // Constructor to initialize the cinema room with a 5x5 matrix of seats
    public CinemaRoom(String movieId) {
        this.movieeId = movieId; // Set the movieId for the cinema room
        seats = new Seat[5][5];
        // Initialize all seats as available
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                seats[i][j] = new Seat();
                seats[i][j].setStatus(SeatStatus.AVAILABLE);
                seats[i][j].setRow(i);
                seats[i][j].setColumn(j);
                seats[i][j].setMovieId(movieId); // Set the movieId for each seat
            }
        }
    }


    // Method to get all seats
    public Seat[][] getAllSeats() {
        return seats;
    }
}
