package com.example.kosherja.Controller.UserController;


import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import com.example.kosherja.Service.UserService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentRepo stdRepo;
    @Autowired
    private TicketRepo tickRepo;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> fetchAllStudents(){return stdRepo.findAll();}

//    @PostMapping
//    public ResponseEntity createStudent(@RequestBody Student student){
//        return ResponseEntity.status(201).body(
//                stdRepo.save(student)
//        );
//
//    }

    //create new student
    @PostMapping("/create/{id}")
    public ResponseEntity<Student> createStudent(@PathVariable String id, @RequestBody Student student) {
        student.setManagerId(id);

        Student createdStudent = stdRepo.save(student);

        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    //create a new Student by selecting the manager, contract and room
    @PostMapping("/createStudent/{managerId}/{roomId}/{contractId}")
    public ResponseEntity<String> createStudentPlus(@PathVariable String managerId,  @PathVariable String roomId, @PathVariable String contractId,@RequestBody Student student) {
        try {
            studentService.createStudent(managerId, student,roomId,contractId);
            return ResponseEntity.ok("Student registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration of Student failed.");
        }


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

//    Chat box for std with service

}
