package com.example.kosherja.Repo;

import com.example.kosherja.Model.Building;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepo extends MongoRepository<Building, String> {
}

