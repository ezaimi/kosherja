package com.example.kosherja.Repo.FacilitiesRepo;

import com.example.kosherja.Model.Facilities.Room;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends MongoRepository<Room,String> {

    List<Room> findByBuildingId(String id);
}
