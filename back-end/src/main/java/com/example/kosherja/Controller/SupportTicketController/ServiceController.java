package com.example.kosherja.Controller.SupportTicketController;


import com.example.kosherja.Model.SupportTicket.Service1;
import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Repo.Meetings.AvailableDtRepo;
import com.example.kosherja.Repo.SupportTicketRepo.Service1Repo;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service1")
public class ServiceController {

//    krijojm nje post per te


    @Autowired
    Service1Repo service1Repo;
    @Autowired
    TicketRepo tickRepo;
    @PutMapping("{servId}/ticket/{tickId}")
    public ResponseEntity<?> updateTicket(@PathVariable String servId, @PathVariable String tickId, @RequestBody List<Ticket> updatedTickets) {
        Service1 service = service1Repo.findById(servId).orElse(null);
        if (service == null) {
            return ResponseEntity.notFound().build();
        }

        for (Ticket updatedTicket : updatedTickets) {
            updatedTicket.setTicketId(tickId);
            tickRepo.save(updatedTicket);
        }

        service.setTicketList(updatedTickets);

        Service1 updatedService = service1Repo.save(service);
        return ResponseEntity.ok(updatedService);
    }


}