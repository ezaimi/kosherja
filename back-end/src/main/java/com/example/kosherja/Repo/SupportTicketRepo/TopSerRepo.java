package com.example.kosherja.Repo.SupportTicketRepo;

import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Model.User.TopSer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopSerRepo  extends MongoRepository<TopSer,String> {
   TopSer findByUsername(String username);
}
