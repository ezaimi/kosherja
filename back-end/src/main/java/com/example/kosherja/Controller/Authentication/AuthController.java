package com.example.kosherja.Controller.Authentication;

import com.example.kosherja.Model.User.LoginRequest;
import com.example.kosherja.Service.Authenticate.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/api/login")
    public Object login(@RequestBody LoginRequest loginRequest) {
        // Try authenticating as admin
        Object adminAuth = userAuthService.authenticateAdmin(loginRequest.getUsername(), loginRequest.getPassword());
        if (adminAuth != null) {
            return adminAuth;

        }

        // Try authenticating as student
        Object studentAuth = userAuthService.authenticateStudent(loginRequest.getUsername(), loginRequest.getPassword());
        if (studentAuth != null) {
            return studentAuth;
        }

        // Try authenticating as manager
        Object managerAuth = userAuthService.authenticateManager(loginRequest.getUsername(), loginRequest.getPassword());
        if (managerAuth != null) {
            return managerAuth;
        }

        // If none of the above matches, return null
        return null;
    }
}
