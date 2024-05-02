package com.example.kosherja.Repo.UserRepo;

import com.example.kosherja.Model.User.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepo extends MongoRepository<Admin,String> {
    Admin findByUsername(String username);

}