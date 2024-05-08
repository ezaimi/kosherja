package com.example.kosherja.Controller.SupportTicketController;

import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Model.SupportTicket.TicketStatus;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import com.example.kosherja.Repo.UserRepo.StdRepo2;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tickets/{stdId}")
public class TicketController {
  @Autowired
  private StdRepo2 stdRepo2;
    @Autowired
    private TicketRepo tickRepo;
    @Autowired
    private StudentRepo stdRepo;

//    1.CREATE A TICKET
//    @PostMapping("/createTicket")
//    public ResponseEntity<Ticket> createTicket(@PathVariable String stdId,@RequestBody Ticket ticket){
//        Ticket ticket1=new Ticket();
//        ticket1.setStudentId(stdId);
//        ticket1.setTopic(ticket.getTopic());
//        ticket1.setPriority(ticket.getPriority());
//        ticket1.setStatus(ticket.getStatus());
//        ticket1.setMsg(ticket.getMsg());
//        ticket1.setCreatedAt(LocalDateTime.now());
//        Optional<Student> optionalStudent = stdRepo.findById(stdId);
//
//        Student studentName = optionalStudent.orElse(null);
//        ticket1.setStdName(studentName.getName());
//
//
//        return new ResponseEntity<>(tickRepo.save(ticket1),HttpStatus.CREATED);
//    }
@PostMapping("/createTicket")
public ResponseEntity<Ticket> createTicket(@PathVariable String stdId, @RequestBody Ticket ticket) {

          Student student=stdRepo2.findById(stdId).orElse(null);
          String stdName=student.getName();
          System.out.println("ardisaa"+stdName);
            Ticket ticket1 = new Ticket();
            ticket1.setStudentId(stdId);
            ticket1.setTopic(ticket.getTopic());
            ticket1.setPriority(ticket.getPriority());
            ticket1.setStatus(ticket.getStatus());
            ticket1.setMsg(ticket.getMsg());
            ticket1.setCreatedAt(LocalDateTime.now());
            ticket1.setStdName(stdName);
            return new ResponseEntity<>(tickRepo.save(ticket1), HttpStatus.CREATED);

}

//    2. FETCH ALL TICKETS OF THAT STUDENT
    @GetMapping("/fetchAllTickets")
    public ResponseEntity<List<Ticket>> getAllTickets(@PathVariable String stdId){
        return new ResponseEntity<>(tickRepo.findTicketBystudentId(stdId),HttpStatus.OK);
    }
//    3. DELETE A TICKET BY TICKETID / nuk e vendosim tek url se behet shm radundant
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTicket(@RequestBody String  tickId) {
        if (tickId != null) {
            tickRepo.deleteByticketId(tickId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }



}
