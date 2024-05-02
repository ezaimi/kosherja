package com.example.kosherja.Repo.UserRepo;

import com.example.kosherja.Model.User.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepo extends MongoRepository<Student,String> {
    long countByManagerId(String id);
<<<<<<< HEAD
    Student findByUsername(String username);
=======

    boolean existsByUsernameOrEmail(String username, String email);
>>>>>>> 7646982990341c54b0bc21121bfe4f0564baa2f7
}
