package com.example.kosherja.Repo;

import com.example.kosherja.Model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends MongoRepository<Room,String> {

}
