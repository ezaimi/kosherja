package com.example.kosherja.Repo.Meetings;

import com.example.kosherja.Model.Meetings.AvailabilityRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvailableDtRepo extends CrudRepository<AvailabilityRequest, String> {
  List< AvailabilityRequest > findByDate(LocalDate date);

}
