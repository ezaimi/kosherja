package com.example.kosherja.Repo.SupportTicketRepo;

import com.example.kosherja.Model.SupportTicket.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends MongoRepository<Ticket,String> {
    List<Ticket> findAllByStudentId(String id);
    List<Ticket> findAll(); // This fetches all tickets
    List<Ticket>findTicketBystudentId(String id);
    void deleteByticketId(String id);
//    Ticket save();
}