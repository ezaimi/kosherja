package com.example.kosherja.Repo;

import com.example.kosherja.Model.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends MongoRepository<Contract,String> {
}
