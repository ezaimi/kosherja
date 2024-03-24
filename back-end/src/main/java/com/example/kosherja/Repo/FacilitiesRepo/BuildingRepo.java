package com.example.kosherja.Repo.FacilitiesRepo;

import com.example.kosherja.Model.Facilities.Building;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepo extends MongoRepository<Building, String> {
}

