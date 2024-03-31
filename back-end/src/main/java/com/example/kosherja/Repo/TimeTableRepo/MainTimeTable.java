package com.example.kosherja.Repo.TimeTableRepo;

import com.example.kosherja.Model.Timetable.TimeTable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MainTimeTable extends MongoRepository<TimeTable,String> {
Optional<TimeTable> findById(String id);
}
