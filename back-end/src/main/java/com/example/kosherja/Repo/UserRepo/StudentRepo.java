package com.example.kosherja.Repo.UserRepo;

import com.example.kosherja.Model.User.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends MongoRepository<Student,String> {
    long countByManagerId(String id);

    Student findByUsername(String username);


    boolean existsByUsernameOrEmail(String username, String email);
}
