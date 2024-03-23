package com.example.kosherja.Controller;


import com.example.kosherja.Model.Student;
import com.example.kosherja.Model.Ticket;
import com.example.kosherja.Repo.StdRepo;
import com.example.kosherja.Repo.TickRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StdCont {



    @Autowired
    private StdRepo stdRepo;
    @Autowired
    private TickRepo tickRepo;

    @GetMapping
    public List<Student> fetchAllStudents(){return stdRepo.findAll();}

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student){
        return ResponseEntity.status(201).body(
                stdRepo.save(student)
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable String id){
        return ResponseEntity.ok(stdRepo.findById(id).orElse(null));
    }


    @GetMapping("ticketsOfStd/{studentId}")
    public ResponseEntity<List<Ticket>> getTicketByStdId(@PathVariable("studentId") String studentId){
        List<Ticket> tickets = tickRepo.findAllByStudentId(studentId);

        if(tickets.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tickets);
        }
    }
}
