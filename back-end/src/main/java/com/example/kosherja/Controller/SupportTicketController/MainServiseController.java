package com.example.kosherja.Controller.SupportTicketController;

import com.example.kosherja.Model.SupportTicket.Services;
import com.example.kosherja.Model.SupportTicket.Ticket;
//import com.example.kosherja.Model.User.MainUserServ;
import com.example.kosherja.Model.User.TopSer;
import com.example.kosherja.Repo.SupportTicketRepo.MainServiceTicketRepo;
import com.example.kosherja.Repo.SupportTicketRepo.Service1Repo;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
//import com.example.kosherja.Service.SupportTicket.MainService;
import com.example.kosherja.Repo.SupportTicketRepo.TopSerRepo;
import com.example.kosherja.Service.SupportTicket.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mainService")
public class MainServiseController{
//    method to fetch all the tickets form all students

    @Autowired
    TicketRepo tickRepo;
    @Autowired
    MainServiceTicketRepo mainServiceTicket;
    @Autowired
    Service1Repo service1Repo;

    @Autowired
    MainService mainService;

    @Autowired
    TopSerRepo topSerRepo;


//
////    0.Create Main service
    @PostMapping("/createMainService")
    public ResponseEntity<TopSer> createMainService(@RequestBody TopSer model){
       TopSer model1=  mainService.createMainService(model);
        return new ResponseEntity<>(topSerRepo.save(model1), HttpStatus.OK);
    }

//    1.Fetch all tickets
    @GetMapping("/fetchTickets")
    public ResponseEntity<List<Ticket>> fetchAllTickets() {




        return new ResponseEntity<>(tickRepo.findAll(), HttpStatus.OK);
    }
//   2.Create different service levels info
    @PostMapping("/createServices")
    public ResponseEntity<Services> create(@RequestBody Services services){
        Services services1=new Services();
        services1.setName(services.getName());
        services1.setUsername(services.getUsername());
        services1.setSurname(services.getSurname());
        services1.setPassword(services.getPassword());
        services1.setEmail(services.getEmail());
        services1.setPhoneNr(services.getPhoneNr());
        services1.setServiseLevels(services.getServiseLevels());
        services1.setTicketList(services.getTicketList());
        return new ResponseEntity<>(service1Repo.save(services1),HttpStatus.CREATED);
    }


    //  3.Send tick to a specific service level
    @PostMapping("/sentTickTo/{servId}/{tickId}")
    public ResponseEntity<Services> sendTicketTo(@PathVariable String servId, @PathVariable String tickId) {
        Services service = service1Repo.findById(servId).orElse(null);
        if (service == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle service not found
        }

        Ticket selectedTicket = mainServiceTicket.findAllByTicketId(tickId);
        if (selectedTicket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle ticket not found
        }

        List<Ticket> ticketList = service.getTicketList();
        ticketList.add(selectedTicket);

        service1Repo.save(service); // Update existing service with new ticket

        return new ResponseEntity<>(service, HttpStatus.OK);
    }


//    @PostMapping("/createService")
//    public ResponseEntity<Service1> createService(@RequestBody Service1 service) {
//        return new ResponseEntity<>(service1Repo.save(service), HttpStatus.CREATED);
//    }


}