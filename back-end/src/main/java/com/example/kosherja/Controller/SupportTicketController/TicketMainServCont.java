package com.example.kosherja.Controller.SupportTicketController;

import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Repo.SupportTicketRepo.MainServiceTicketRepo;
import com.example.kosherja.Repo.SupportTicketRepo.Service1Repo;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import com.example.kosherja.Service.SupportTicket.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.kosherja.Model.SupportTicket.Service1;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mainService")
public class TicketMainServCont {
//    method to fetch all the tickets form all students

    @Autowired
    TicketRepo tickRepo;
    @Autowired
    MainServiceTicketRepo mainServiceTicket;
    @Autowired
    Service1Repo service1Repo;

    @Autowired
    MainService mainService;


    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> fetchAllTickets() {
        System.out.println("ardisa");
        System.out.println(tickRepo.findAll());
        return new ResponseEntity<>(tickRepo.findAll(), HttpStatus.OK);
    }

    //    send ticket to service with level x
    @PostMapping("/sentTickTo/{servId}/{tickId}")
    public ResponseEntity<Service1> sendTicketTo(@PathVariable String servId, @PathVariable String tickId) {
        Ticket selectedTicket = mainServiceTicket.findAllByTicketId(tickId);
        Service1 service = service1Repo.findById(servId).orElse(null);

        service.getTicketList().add(selectedTicket);

        return new ResponseEntity<>(service1Repo.save(service), HttpStatus.CREATED);
    }

    @PostMapping("/createService")
    public ResponseEntity<Service1> createService(@RequestBody Service1 service) {
        return new ResponseEntity<>(service1Repo.save(service), HttpStatus.CREATED);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void>deleteServiceById(@PathVariable String id){
        mainService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}