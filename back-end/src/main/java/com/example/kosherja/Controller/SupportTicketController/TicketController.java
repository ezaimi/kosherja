package com.example.kosherja.Controller.SupportTicketController;

import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketRepo tickRepo;
    @Autowired
    private StudentRepo stdRepo;

    @GetMapping
    public List<Ticket> fetchAllTickets(){
        return tickRepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity getTicketById(@PathVariable String id){
        return ResponseEntity.ok(tickRepo.findById(id).orElse(null));

    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Ticket> createTicket(@PathVariable String id,@RequestBody Ticket ticket) {
        //Perdorim @ResuqestBody sepse converton Json data into Java objects.
        // Spring expects the  Http request body to contain data in a specific format Json or xml
        // Since we are excpecting a Json and then we need to convert it into a ojbect=>Deseralization
        //  For deserzliation we have to use @JsonCreated




//        Ticket createdTicket = tickRepo.save(ticket);
//        createdTicket.setStudentId(id);
//
//        return new ResponseEntity<>(createdTicket,HttpStatus.CREATED);
        ticket.setStudentId(id);

        // Save the ticket with the updated studentId
        Ticket createdTicket = tickRepo.save(ticket);

        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }


}
