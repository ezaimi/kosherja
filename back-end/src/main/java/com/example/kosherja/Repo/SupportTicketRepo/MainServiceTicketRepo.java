package com.example.kosherja.Repo.SupportTicketRepo;

import com.example.kosherja.Model.SupportTicket.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainServiceTicketRepo extends MongoRepository<Ticket,String> {

    Ticket findAllByTicketId(String TicketId);

}