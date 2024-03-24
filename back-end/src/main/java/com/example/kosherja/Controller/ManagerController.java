package com.example.kosherja.Controller;

import com.example.kosherja.Model.Manager;
import com.example.kosherja.Repo.ManagerRepo;
import com.example.kosherja.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerRepo managerRepo;
    @Autowired
    private ManagerService managerService;

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
}
