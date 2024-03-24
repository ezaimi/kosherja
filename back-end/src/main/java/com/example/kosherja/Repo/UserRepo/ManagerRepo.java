package com.example.kosherja.Repo.UserRepo;

import com.example.kosherja.Model.User.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends MongoRepository<Manager, String> {


    boolean existsByUsernameOrEmail(String username, String email);

}
