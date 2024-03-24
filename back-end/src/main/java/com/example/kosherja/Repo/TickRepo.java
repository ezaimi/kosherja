package com.example.kosherja.Repo;

import com.example.kosherja.Model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TickRepo extends MongoRepository<Ticket,String> {
    List<Ticket> findAllByStudentId(String id);
}