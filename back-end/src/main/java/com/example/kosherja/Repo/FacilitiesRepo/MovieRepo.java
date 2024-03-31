package com.example.kosherja.Repo.FacilitiesRepo;

import com.example.kosherja.Model.Facilities.Genre;
import com.example.kosherja.Model.Facilities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepo extends MongoRepository<Movie,String> {
   List<Movie> findAllByGenre(Genre genre);
   Optional<Movie> findById(String id);

   Optional<Movie>findMovieNameById(String id);
}
