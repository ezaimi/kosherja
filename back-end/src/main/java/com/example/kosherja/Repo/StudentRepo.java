package com.example.kosherja.Repo;

import com.example.kosherja.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends MongoRepository<Student,String> {
    long countByManagerId(String id);
}
