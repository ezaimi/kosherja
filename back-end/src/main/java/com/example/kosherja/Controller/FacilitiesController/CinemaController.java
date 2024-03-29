package com.example.kosherja.Controller.FacilitiesController;

import com.example.kosherja.Model.Facilities.Genre;
import com.example.kosherja.Model.Facilities.Movie;
import com.example.kosherja.Service.Cinema.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Autowired
    MovieService movieService;
    @PostMapping("/create")
    public ResponseEntity<Movie>create(@RequestBody  Movie moives){
        return new ResponseEntity<>(movieService.createMovie(moives), HttpStatus.CREATED);
    }
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>>showAllMoviesWithSameGenre(@PathVariable Genre genre){
        return new ResponseEntity<>(movieService.findAllByGenre(genre),HttpStatus.OK);
    }

    @GetMapping("/movieId/{id}")
    public ResponseEntity<Movie>getSingleMovie(@PathVariable String id){
        return new ResponseEntity<>(movieService.findById(id).orElse(null),HttpStatus.OK);
    }
}
