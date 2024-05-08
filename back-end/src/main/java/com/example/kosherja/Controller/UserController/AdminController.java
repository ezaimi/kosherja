package com.example.kosherja.Controller.UserController;



import com.example.kosherja.Model.User.Admin;
import com.example.kosherja.Model.User.ManagerDTO;
import com.example.kosherja.Model.User.Student;
import com.example.kosherja.Repo.UserRepo.AdminRepo;
import com.example.kosherja.Service.UserService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepo adminRepo;

    @GetMapping("/coordinators/list")
    public ResponseEntity<List<ManagerDTO>> getAllCoordinatorDetails(){
        List<ManagerDTO> coordinatorDetails = adminService.getAllCoordinatorsDetails();
        return new ResponseEntity<>(coordinatorDetails, HttpStatus.OK);
    }

    //create an admin
    @PostMapping("/create/admin")
    public ResponseEntity<Admin> createStudent(@RequestBody Admin admin) {

        Admin createdAdmin = adminRepo.save(admin);

        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

}
