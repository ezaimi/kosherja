package com.example.kosherja.Repo.UserDetailsRepo;

import com.example.kosherja.Model.UserDetails.Documents;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends MongoRepository<Documents, String> {
}
