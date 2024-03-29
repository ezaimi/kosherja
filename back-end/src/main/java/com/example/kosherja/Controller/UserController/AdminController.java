package com.example.kosherja.Controller.UserController;



import com.example.kosherja.Model.User.ManagerDTO;
import com.example.kosherja.Service.UserService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coordinators")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    public ResponseEntity<List<ManagerDTO>> getAllCoordinatorDetails(){
        List<ManagerDTO> coordinatorDetails = adminService.getAllCoordinatorsDetails();
        return new ResponseEntity<>(coordinatorDetails, HttpStatus.OK);
    }

}
