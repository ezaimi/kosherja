package com.example.kosherja.Controller.UserController;


import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.SupportTicket.Ticket;
import com.example.kosherja.Model.UserDetails.Contract;
import com.example.kosherja.Repo.SupportTicketRepo.TicketRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import com.example.kosherja.Service.UserService.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
<<<<<<< HEAD
    public ResponseEntity<String> createStudentPlus(@PathVariable String managerId,  @PathVariable String roomId, @PathVariable String contractId,@RequestBody Student student) {
        try {
            studentService.createStudent(managerId, student,roomId,contractId);
            return ResponseEntity.ok("Student registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration of Student failed.");
=======
    public ResponseEntity<?> createStudentPlus(@PathVariable("managerId") String managerId, @RequestBody Student student, @PathVariable("roomId") String roomId, @PathVariable("contractId") String contractId) {

        if (studentService.existsByUsernameOrEmail(student.getUsername(), student.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student with the same username or email already exists");
        } else {

            Student studentCreated = studentService.createStudent(managerId, student,roomId,contractId);

            return new ResponseEntity<>(studentCreated, HttpStatus.CREATED);
>>>>>>> 7646982990341c54b0bc21121bfe4f0564baa2f7
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable String id){
        return ResponseEntity.ok(stdRepo.findById(id).orElse(null));
    }


    //a method to update student info
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editStudent(@PathVariable("id") String id, @RequestBody Student editedStudent) {

        Optional<Student> existingStudentOptional = stdRepo.findById(id);
        if (existingStudentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Call the service method to edit the student
        Student existingStudent = existingStudentOptional.get();
        Student updatedStudent = studentService.editStudent(existingStudent.getId(), editedStudent);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to edit student.");
        }

        return ResponseEntity.ok(updatedStudent);
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

<<<<<<< HEAD
//    Chat box for std with service
=======

>>>>>>> 7646982990341c54b0bc21121bfe4f0564baa2f7

}
