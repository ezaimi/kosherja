package com.example.kosherja.Service.SupportTicket;

import com.example.kosherja.Repo.SupportTicketRepo.Service1Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    private Service1Repo service1Repo;

    public void deleteService(String id){
        service1Repo.deleteById(id);
    }
}
