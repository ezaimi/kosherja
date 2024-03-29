package com.example.kosherja.Service.Cinema;

import com.example.kosherja.Model.Facilities.Genre;
import com.example.kosherja.Model.Facilities.Movie;
import com.example.kosherja.Repo.FacilitiesRepo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepo movieRepo;

    public Movie createMovie(Movie movie){
        return movieRepo.save(movie);
    }

    public List<Movie> findAllByGenre(Genre genre){
        return movieRepo.findAllByGenre(genre);
    }

    public Optional<Movie> findById(String id){
        return movieRepo.findById(id);
    }
}
