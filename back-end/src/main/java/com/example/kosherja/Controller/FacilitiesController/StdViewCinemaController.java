package com.example.kosherja.Controller.FacilitiesController;

import com.example.kosherja.Model.Facilities.*;
import com.example.kosherja.Repo.FacilitiesRepo.SeatRepo;
import com.example.kosherja.Service.Cinema.CinemaRoomService;
import com.example.kosherja.Service.Cinema.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cinema")
public class StdViewCinemaController {
    @Autowired
    MovieService movieService;
    @Autowired
    CinemaRoomService cinemaRoomService;
    @Autowired
    SeatRepo seatRepo;
    @PostMapping("/create")
    public ResponseEntity<Movie>create(@RequestBody  Movie moives){
        return new ResponseEntity<>(movieService.createMovie(moives), HttpStatus.CREATED);
    }
//   1.Show all genres
    @GetMapping("/showAllGenres")
    public ResponseEntity<List<Genre>> showAllGenres() {
        List<Genre> allGenres = Arrays.asList(Genre.values());
        return new ResponseEntity<>(allGenres, HttpStatus.OK);
    }
//    2.Find all movies by genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>>showAllMoviesWithSameGenre(@PathVariable Genre genre){
        return new ResponseEntity<>(movieService.findAllByGenre(genre),HttpStatus.OK);
    }
//   3.Kur selekton nje movie te dalin te dhenat e filmit
    @GetMapping("/movieId/{id}")
    public ResponseEntity<Movie>getSingleMovie(@PathVariable String id){
        return new ResponseEntity<>(movieService.findById(id).orElse(null),HttpStatus.OK);
    }
//   4.Nje student i ben book nje seati
@PostMapping("/{stdId}")
public ResponseEntity<Seat> bookSeat(@PathVariable String stdId, @RequestBody Map<String, String> requestBody) {
    String seatId = requestBody.get("seatId");
    if (seatId == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Optional<Seat> optionalSeat = seatRepo.findById(seatId);
    if (optionalSeat.isPresent()) {
        Seat seat = optionalSeat.get();
        seat.setStatus(SeatStatus.RESERVED);
        seat.setStdId(stdId);
        Seat updatedSeat = seatRepo.save(seat);
        return new ResponseEntity<>(updatedSeat, HttpStatus.CREATED);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


}
