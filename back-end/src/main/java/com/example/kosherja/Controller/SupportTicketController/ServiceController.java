package com.example.kosherja.Controller.SupportTicketController;


import com.example.kosherja.Model.SupportTicket.Services;
import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Model.SupportTicket.TicketStatus;
import com.example.kosherja.Repo.Meetings.AvailableDtRepo;
import com.example.kosherja.Repo.SupportTicketRepo.Service1Repo;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/services/{servId}")
public class ServiceController {

//    krijojm nje post per te


    @Autowired
    Service1Repo service1Repo;
    @Autowired
    TicketRepo tickRepo;
//    1.A service level updates the ticket by adding a msg (chat box) and updats the status
    @PutMapping("/update/{tickId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable String tickId, @PathVariable String servId, @RequestBody Map<String, Object> update) {
        // Check if the service exists
        Services services = service1Repo.findById(servId).orElse(null);
        if (services == null) {
            return ResponseEntity.notFound().build();
        }

        // Retrieve the ticket
        Ticket ticket = tickRepo.findById(tickId).orElse(null);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }

        // Update ticket properties
        if (update.containsKey("msg")) {
            List<String> newMsg = (List<String>) update.get("msg");
            ticket.getMsg().addAll(newMsg);
        }

        if (update.containsKey("status")) {
            String newStatus = (String) update.get("status");
            ticket.setStatus(TicketStatus.REJECTED); // This should probably use newStatus instead
        }

        // Save the updated ticket
        Ticket updatedTicket = tickRepo.save(ticket);

        // Update the service's ticket list
        List<Ticket> ticketList = services.getTicketList();
        if (ticketList == null) {
            ticketList = new ArrayList<>();
        }
        ticketList.removeIf(t -> t.getTicketId().equals(tickId)); // Remove the old ticket
        ticketList.add(updatedTicket); // Add the updated ticket
        services.setTicketList(ticketList);
//        service1Repo.save(services);

        return ResponseEntity.ok(updatedTicket);
    }


//    2.Fetch all tickets of that service
@GetMapping("/ticketList")
public ResponseEntity<List<Ticket>> getTicketListForService(@PathVariable String servId) {
    Services service = service1Repo.findById(servId).orElse(null);
    if (service == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle service not found
    }

    List<Ticket> ticketList = service.getTicketList();
    return new ResponseEntity<>(ticketList, HttpStatus.OK);
}


}

