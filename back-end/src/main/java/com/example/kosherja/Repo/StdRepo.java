package com.example.kosherja.Repo;

import com.example.kosherja.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface StdRepo extends MongoRepository<Student,String> {
//   Optional<Student> findByAmxa(String amxa);

    }

