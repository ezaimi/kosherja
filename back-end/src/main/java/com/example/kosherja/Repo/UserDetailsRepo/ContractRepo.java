package com.example.kosherja.Repo.UserDetailsRepo;

import com.example.kosherja.Model.UserDetails.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends MongoRepository<Contract,String> {
}
