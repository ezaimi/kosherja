package com.example.kosherja.Repo.TimeTableRepo;

import com.example.kosherja.Model.Timetable.Routes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepo extends MongoRepository<Routes,String> {
}
