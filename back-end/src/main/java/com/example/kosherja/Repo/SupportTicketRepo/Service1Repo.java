package com.example.kosherja.Repo.SupportTicketRepo;

import com.example.kosherja.Model.SupportTicket.Service1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Service1Repo extends MongoRepository<Service1,String> {
}