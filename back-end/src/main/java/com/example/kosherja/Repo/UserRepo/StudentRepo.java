package com.example.kosherja.Repo.UserRepo;

import com.example.kosherja.Model.User.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepo extends MongoRepository<Student,String> {
    long countByManagerId(String id);

    Student findByUsername(String username);


    boolean existsByUsernameOrEmail(String username, String email);
    List<Student> findByManagerId(String managerId);



}
