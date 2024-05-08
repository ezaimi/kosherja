package com.example.kosherja.Controller.UserController;

import com.example.kosherja.Model.Facilities.Room;
import com.example.kosherja.Model.User.Manager;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Model.UserDetails.Contract;
import com.example.kosherja.Repo.UserRepo.ManagerRepo;
import com.example.kosherja.Repo.UserRepo.StudentRepo;
import com.example.kosherja.Service.Facilities.RoomService;
import com.example.kosherja.Service.UserDetails.ContractService;
import com.example.kosherja.Service.UserService.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerRepo managerRepo;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ContractService contractService;

//for the admin dashboard
    @PostMapping("/add")
    public ResponseEntity<?> createCoordinator(@RequestBody Manager coordinator) {

        //if a manager with the same credentials already exists
        if (managerService.existsByUsernameOrEmail(coordinator.getUsername(), coordinator.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Coordinator with the same username or email already exists");
        } else {

            Manager createdCoordinator = managerService.createCoordinator(coordinator);

            return new ResponseEntity<>(createdCoordinator, HttpStatus.CREATED);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCoordinator(@PathVariable String id){
        boolean deleted = managerService.deleteCoordinatorById(id);

        if (deleted) {
            return ResponseEntity.ok("Coordinator deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping("/create")
//    public ResponseEntity createManager(@RequestBody Manager student){
//        return ResponseEntity.status(201).body(
//                managerRepo.save(student)
//        );
//
//    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Manager> createTicket(@PathVariable String id,@RequestBody Manager manager) {
        manager.setBuildingID(id);

        Manager createdManager = managerRepo.save(manager);

        return new ResponseEntity<>(createdManager, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/getAllStd")
    public ResponseEntity<List<Student>> fetchStd(@PathVariable String id){
        Manager manager = managerRepo.findById(id).orElse(null);
        if(manager == null){
            return ResponseEntity.notFound().build();
        }

        List<Student> students = studentRepo.findByManagerId(id); // Assuming you have a method like this in your studentRepo

        // Set the manager for each student

        students.forEach(student -> {
            student.setManager(manager);
            Room room = roomService.getRoomById(student.getRoomId());
            Contract contract = contractService.getContractById(student.getContractId()); // Assuming you have a contract service

            student.setRoomm(room); // Set the fetched room to the student
            student.setContractt(contract);
        });


        return new ResponseEntity<>(students, HttpStatus.OK);
    }


}
